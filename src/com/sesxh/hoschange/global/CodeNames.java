package com.sesxh.hoschange.global;

import com.sesxh.frame.base.BaseCodeNames;

public class CodeNames extends BaseCodeNames {
	/**
	 * 任务内容类型
	 */
	public final static class RWNRLX {
		public final static String dbProcedure = "001";
		public final static String javaCode = "002";
	}
	/**
	 * 有效标志
	 */
	public final static class YXBZ {
		public final static String YX = "1";//有效
		public final static String WX = "0";//无效
	}
	// 数据权限类别
	public final static class SJLB {
		public final static String ORG = "010"; // 机构
	}
	// 数据级别
	public final static class SJJB {
		public final static String DQ = "1"; // 当前数据
		public final static String DQYXJ = "2";// 当前以及其所有下级数据
		public final static String XJ = "3";// 当前的所有下级数据
	}
	// 设备类型
		public final static class SBLX {
			public final static String XG = "ShoeBox"; // 当前数据
			public final static String YG = "ClothesPress";// 当前以及其所有下级数据
			public final static String HST = "RecyclingBin";// 当前的所有下级数据
		}
	
	
}
