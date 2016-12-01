package net.dqsy.papermg.sysmanager.service.impl;

import net.dqsy.papermg.papermanager.po.PaperTitle;
import net.dqsy.papermg.sysmanager.po.PaperMessage;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.sysmanager.po.PaperUser;
import net.dqsy.papermg.sysmanager.po.PaperUserRole;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;

import net.dqsy.papermg.sysmanager.po.PaperMessage;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MsgService extends HibernateDaoSupport {
	public int send(PaperMessage msg) {
		try {
			msg.setSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
			msg.setFlag(0);
			getHibernateTemplate().save(msg);
			getHibernateTemplate().flush();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int sendTo(String sender, String receiver, String msgTitle, String msgText) {
		try {
			String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			PaperMessage msg = new PaperMessage(sender, receiver, timeStr, msgTitle, msgText, 0);
			getHibernateTemplate().save(msg);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int save(String receiver, String msgText, HttpSession session) {
		try {
			PaperUser user = (PaperUser) session.getAttribute("user");
			if (user == null) {
				return 0;
			}

			PaperStudent stu = (PaperStudent) session.getAttribute("student");
			PaperTeacher tea = (PaperTeacher) session.getAttribute("teacher");
			String realName = stu == null ? tea.getTeacherName() : tea == null ? "未知" : stu.getStudentName();

			String sender = user.getUserName();
			String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			PaperMessage msg = new PaperMessage(sender, receiver, timeStr, realName, msgText, 0);

			getHibernateTemplate().save(msg);
			getHibernateTemplate().flush();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int sysSend(String sender, String msgText, HttpSession session) {
		try {
			PaperUser user = (PaperUser) session.getAttribute("user");
			if (user == null) {
				return 0;
			}

			List stuList = getHibernateTemplate().find("select studentName from PaperStudent where studentNumber=?",
					new Object[] { sender });
			List teaList = getHibernateTemplate().find("select teacherName from PaperTeacher where teacherNumber=?",
					new Object[] { sender });
			String realName = stuList.size() == 0 ? (String) teaList.get(teaList.size() - 1)
					: teaList.size() == 0 ? "未知" : (String) stuList.get(stuList.size() - 1);

			String receiver = user.getUserName();
			String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			PaperMessage msg = new PaperMessage(sender, receiver, timeStr, realName, msgText, 0);

			getHibernateTemplate().save(msg);
			getHibernateTemplate().flush();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<PaperMessage> getNewMsg(HttpSession session) {
		try {
			PaperUser user = (PaperUser) session.getAttribute("user");
			if (user == null) {
				return null;
			}

			String userName = user.getUserName();
			List list = getHibernateTemplate().find("from PaperMessage as msg where msg.receiver=? and msg.flag=0",
					new Object[] { userName });

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<PaperMessage> getAllMsg(String userName) {
		try {
			List list = getHibernateTemplate().find("from PaperMessage as msg where msg.receiver=?",
					new Object[] { userName });
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int overRead(int msgId) {
		PaperMessage msg = null;
		try {
			List list = getHibernateTemplate().find("from PaperMessage as msg where msg.msgId=?",
					new Object[] { Integer.valueOf(msgId) });
			if ((list != null) && (list.size() > 0)) {
				msg = (PaperMessage) list.get(0);
				msg.setFlag(1);
				getHibernateTemplate().update(msg);
				getHibernateTemplate().flush();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*public int sendToLeader(String msgText, HttpSession session) {
		try {
			List list = getHibernateTemplate().find("from PaperUserRole ur where ur.paperRole.roleId=2");
			for (PaperUserRole ur : list) {
				save(ur.getPaperUser().getUserName(), msgText, session);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}*/

/*	public int sendToMyTeacher(String msgText, HttpSession session) {
		try {
			PaperUser user = (PaperUser) session.getAttribute("user");
			List list = getHibernateTemplate()
					.find("from PaperTitle where paperStudent.studentNumber = '" + user.getUserName() + "'");
			for (PaperTitle t : list) {
				save(t.getPaperTeacher().getTeacherNumber(), msgText, session);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}*/
}