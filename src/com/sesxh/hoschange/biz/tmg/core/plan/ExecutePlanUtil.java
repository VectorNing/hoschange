package com.sesxh.hoschange.biz.tmg.core.plan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sesxh.common.exception.FrameException;

/**
 * 调度计划处理类
 */
public class ExecutePlanUtil {
	/**
	 * 获得触发器列表
	 * 
	 * @param plan
	 * @return List<SesTaskTrigger>
	 * @throws FrameException
	 */
	@SuppressWarnings("unchecked")
	public static List<SesTaskTrigger> getTriggerList(String plan) throws FrameException {
		Document doc;
		Element root;
		Iterator<?> termIte;
		List<SesTaskTrigger> sesTaskTrigger = new ArrayList<SesTaskTrigger>();
		String cron = null;
		int timeout, i = 0;
		String manual;
		try {
			doc = DocumentHelper.parseText(plan);
			root = doc.getRootElement();
			List<Element> tParaNodes = root.elements("trigger");
			for (Element t : tParaNodes) {
				SesTaskTrigger sesTask = new SesTaskTrigger();
				cron = t.elementText("cron");
				sesTask.setCron(cron);
				termIte = t.elementIterator("terminate");
				if (termIte.hasNext()) {
					Element termElm = (Element) termIte.next();
					String time = termElm.elementTextTrim("timeout");
					if ("".equals(time) || time == null)
						timeout = 0;
					else
						timeout = Integer.parseInt(time);
					sesTask.setTimeout(timeout);
					manual = termElm.elementTextTrim("manual");
					if ("".equals(manual)) {
						sesTask.setManualTerminate(true);
					} else {
						sesTask.setManualTerminate(false);
					}
				}
				sesTaskTrigger.add(i, sesTask);
				i++;
			}
		} catch (DocumentException e) {
			throw new FrameException(e);
		}
		return sesTaskTrigger;
	}

	/**
	 * 获得调度计划
	 * 
	 * @param triggerList
	 * @return String
	 */
	public static String getPlan(List<SesTaskTrigger> triggerList) {
		String xmlStr = "";
		Document document = DocumentHelper.createDocument();
		SesTaskTrigger sesTask = new SesTaskTrigger();
		Element rootElement = document.addElement("plan");
		rootElement.addAttribute("version", "1.0");
		for (int i = 0; i < triggerList.size(); i++) {
			sesTask = triggerList.get(i);
			String cron = sesTask.getCron();
			Element triggerEle = rootElement.addElement("trigger");
			Element cronEle = triggerEle.addElement("cron");
			cronEle.addText(cron);
			Element termEle = triggerEle.addElement("terminate");
			if (sesTask.isManualTerminate()) {
				termEle.addElement("manual");
			}
			if (sesTask.getTimeout() == 0) {
			} else {
				Element timeEle = termEle.addElement("timeout");
				timeEle.addText(sesTask.getTimeout() + "");
			}
		}
		xmlStr = document.asXML().substring(38).trim();
		return xmlStr;
	}
}
