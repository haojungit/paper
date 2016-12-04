package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperCouncilDAO;
import net.dqsy.papermg.papermanager.dao.PaperCouncilmanDAO;
import net.dqsy.papermg.papermanager.dao.impl.PaperCouncilDAOImpl;
import net.dqsy.papermg.papermanager.po.PaperCouncil;
import net.dqsy.papermg.papermanager.po.PaperCouncilman;
import net.dqsy.papermg.papermanager.service.PaperCouncilService;
import net.dqsy.papermg.sysmanager.po.PaperRole;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.sysmanager.po.PaperUser;
import net.dqsy.papermg.sysmanager.po.PaperUserRole;
import net.dqsy.papermg.sysmanager.service.PaperRoleService;
import net.dqsy.papermg.sysmanager.service.PaperTeacherService;
import net.dqsy.papermg.sysmanager.service.PaperUserRoleService;
import net.dqsy.papermg.sysmanager.service.PaperUserService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PaperCouncilServiceImpl
        implements PaperCouncilService
{
    @Autowired
    private PaperCouncilDAO paperCouncilDAO;
    @Autowired
    private PaperTeacherService paperTeacherService;
    @Autowired
    private PaperCouncilmanDAO paperCouncilmanDAO;
    @Autowired
    private PaperUserRoleService paperUserRoleService;
    @Autowired
    private PaperUserService paperUserService;
    @Autowired
    private PaperRoleService paperRoleService;

    public boolean save(Object o) {
        try {
            this.paperCouncilDAO.save((PaperCouncil) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Object o) {
        try {
            this.paperCouncilDAO.update((PaperCouncil) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        try {
            return this.paperCouncilDAO.find(hql, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaperCouncil findById(int id) {
        try {
            return (PaperCouncil) this.paperCouncilDAO.findById(
                    "com.pactera.papermg.papermanager.po.PaperCouncil", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperCouncilDAO.findAll(
                    "PaperCouncil as c order by c.state", numberOfPage,
                    countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperCouncilDAO = new PaperCouncilDAOImpl();
            this.paperCouncilDAO.delete((PaperCouncil) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        return null;
    }

    public String apply(PaperCouncil paperCouncil, List<Integer> leaders, List<String> members) {
        PaperTeacher paperTeacher = null;
        PaperCouncilman paperCouncilman = null;
        try {
            this.paperCouncilDAO.save(paperCouncil);

            for (int i = 0; i < members.size(); i++) {
                paperTeacher = (PaperTeacher) this.paperTeacherService
                        .findById(((Integer) leaders.get(i)).intValue());
                paperCouncilman = new PaperCouncilman(paperCouncil,
                        paperTeacher, (String) members.get(i), Integer.valueOf(0));
                this.paperCouncilmanDAO.save(paperCouncilman);
            }
            paperTeacher = (PaperTeacher) this.paperTeacherService.findById(
                    ((Integer) leaders
                            .get(leaders.size() - 1)).intValue());
            paperCouncilman = new PaperCouncilman(paperCouncil, paperTeacher,
                    null, Integer.valueOf(1));
            this.paperCouncilmanDAO.save(paperCouncilman);

            return "答辩委员会申报成功,等待领导审批!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "答辩委员会申报失败!";
    }

    public String approve(PaperCouncil paperCouncil) {
        try {
            PaperCouncil council = findById(paperCouncil.getCouncilId().intValue());

            council.setDeanSug(paperCouncil.getDeanSug());
            council.setDeanDate(paperCouncil.getDeanDate());
            council.setState(paperCouncil.getState());

            update(council);

            if (council.getState().intValue() == 1) {
                PaperCouncilman paperCouncilman = null;
                Set councilmanSet = council.getCouncilman();
                Iterator iterator = councilmanSet.iterator();
                while (iterator.hasNext()) {
                    paperCouncilman = (PaperCouncilman) iterator.next();
                    int userId = paperCouncilman.getPaperTeacher()
                            .getPaperUser().getUserId().intValue();

                    List list = null;
                    String[] userIDs = new String[0];
                    String GroupMembers = paperCouncilman.getGroupMember();
                    if (GroupMembers != null) {
                        userIDs = GroupMembers.split(",");
                    }
                    for (int i = -1; i < userIDs.length; i++) {
                        if (i != -1) {
                            PaperTeacher paperTeacher = (PaperTeacher) this.paperTeacherService
                                    .findById(Integer.parseInt(userIDs[i]));
                            userId = paperTeacher.getPaperUser().getUserId().intValue();
                        }

                        list = this.paperUserRoleService.find(
                                "from PaperUserRole where paperUser.userId = " +
                                        userId + " and paperRole.roleId = 3",
                                1, 1).getList();

                        if (list.size() != 0) {
                            continue;
                        }
                        PaperUser paperUser = (PaperUser) this.paperUserService
                                .findById(userId);

                        PaperRole role = new PaperRole();
                        PaperUserRole ur = new PaperUserRole();

                        if (paperUser != null) {
                            role = (PaperRole) this.paperRoleService.findById(3);
                            ur.setPaperRole(role);
                            ur.setPaperUser(paperUser);
                            ur.setFlag(Integer.valueOf(1));
                            this.paperUserRoleService.save(ur);
                        }
                    }
                }

            }

            return "审批成功!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "审批失败!";
    }

    public List findCouncilByManId(int manId) {
        return null;
    }

}