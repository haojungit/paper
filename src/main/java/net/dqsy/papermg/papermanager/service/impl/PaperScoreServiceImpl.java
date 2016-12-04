package net.dqsy.papermg.papermanager.service.impl;

import net.dqsy.papermg.papermanager.dao.PaperScoreDAO;
import net.dqsy.papermg.papermanager.dao.PaperTitleDAO;
import net.dqsy.papermg.papermanager.po.PaperScore;
import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.papermanager.service.PaperScoreService;
import net.dqsy.papermg.util.PagingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperScoreServiceImpl
        implements PaperScoreService
{
    @Autowired
    private PaperScoreDAO paperScoreDAO;
    @Autowired
    private PaperTitleDAO paperTitleDAO;

    public boolean save(Object o) {
        try {
            this.paperScoreDAO.save((PaperScore) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Object o) {
        try {
            this.paperScoreDAO.update((PaperScore) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PagingSupport find(String hql, int numberOfPage, int countOfPage) {
        try {
            return this.paperScoreDAO.find(hql, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object findById(int id) {
        try {
            return (PaperScore) this.paperScoreDAO.findById("com.pactera.papermg.papermanager.po.PaperScore", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, String value, int numberOfPage, int countOfPage) {
        try {
            return this.paperScoreDAO.findByProperty("PaperScore", property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findByProperty(String property, int value, int numberOfPage, int countOfPage) {
        try {
            return this.paperScoreDAO.findByProperty("PaperScore", property, value, numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagingSupport findAll(int numberOfPage, int countOfPage) {
        try {
            return this.paperScoreDAO.findAll("PaperScore", numberOfPage, countOfPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean del(Object o) {
        try {
            this.paperScoreDAO.delete((PaperScore) o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PaperScore findScoreByTitleId(int id) {
        try {
            List scoreList = this.paperTitleDAO.find("from PaperScore where paperTitle.paperTitleId = " + id, 1, 999).getList();

            if ((scoreList == null) || (scoreList.size() == 0)) {
                PaperTitle t = (PaperTitle) this.paperTitleDAO.findById("com.pactera.papermg.papermanager.po.PaperTitle", id);

                if (t == null) {
                    return null;
                }

                PaperScore s = new PaperScore();
                this.paperTitleDAO.find("update paper_score set PaperTitleId=" + id, 1, 999);

                s.setPaperTitle(t);

                this.paperScoreDAO.save(s);

                scoreList.add(s);
            }

            PaperScore score = (PaperScore) scoreList.get(scoreList.size() - 1);

            List paList = this.paperTitleDAO.find("select score from PaperApply where paperTitle.paperTitleId=" + id, 1, 999).getList();
            String strScore = "";
            if ((paList != null) && (paList.size() != 0)) {
                strScore = (String) paList.get(paList.size() - 1);
            }

            if ((strScore != null) && (strScore.length() != 0)) {
                String[] str = strScore.split(",");
                float fScore = Float.parseFloat(str[(str.length - 1)]);
                score.setTscore(fScore);
            }
            double s1 = score.getTscore();
            double s2 = Double.valueOf(score.getPaperTitle().getPaperRecord().getScore()).doubleValue();
            score.setCommitteeScore((float) s2);

            score.setTotalScore((float) (s1 * 0.4D + s2 * 0.6D));
            return score;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int teacherInput(int titleId, String comment, double score) {
        try {
            List scoreList = this.paperTitleDAO.find("from PaperScore where paperTitle.paperTitleId = " + titleId, 1, 999).getList();
            if ((scoreList == null) || (scoreList.size() == 0)) {
                PaperScore sc = new PaperScore();
                PaperTitle t = (PaperTitle) this.paperTitleDAO.findById("com.pactera.papermg.papermanager.po.PaperTitle", titleId);
                sc.setPaperTitle(t);
                scoreList.add(sc);
            }
            PaperScore sco = (PaperScore) scoreList.get(scoreList.size() - 1);
            sco.setTcomment(comment);
            sco.setTscore((float) score);
            System.out.println(sco.getScoreId() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            if (sco.getScoreId() == null)
                save(sco);
            else {
                update(sco);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int teamInput(int titleId, String comment, double score) {
        try {
            List scoreList = this.paperTitleDAO.find("from PaperScore where paperTitle.paperTitleId = " + titleId, 1, 999).getList();
            if ((scoreList == null) || (scoreList.size() == 0)) {
                PaperScore sc = new PaperScore();
                PaperTitle t = (PaperTitle) this.paperTitleDAO.findById("com.pactera.papermg.papermanager.po.PaperTitle", titleId);
                sc.setPaperTitle(t);
                scoreList.add(sc);
            }
            PaperScore sco = (PaperScore) scoreList.get(scoreList.size() - 1);
            sco.setCommitteeComment(comment);
            sco.setCommitteeScore((float) score);
            System.out.println(sco.getScoreId() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            if (sco.getScoreId() == null)
                save(sco);
            else {
                update(sco);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int councilInput(int titleId, String comment) {
        try {
            List scoreList = this.paperTitleDAO.find("from PaperScore where paperTitle.paperTitleId = " + titleId, 1, 999).getList();
            if ((scoreList == null) || (scoreList.size() == 0)) {
                PaperScore sc = new PaperScore();
                PaperTitle t = (PaperTitle) this.paperTitleDAO.findById("com.pactera.papermg.papermanager.po.PaperTitle", titleId);
                sc.setPaperTitle(t);
                scoreList.add(sc);
            }
            PaperScore sco = (PaperScore) scoreList.get(scoreList.size() - 1);
            sco.setCouncilComment(comment);
            System.out.println(sco.getScoreId() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            if (sco.getScoreId() == null)
                save(sco);
            else {
                update(sco);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}