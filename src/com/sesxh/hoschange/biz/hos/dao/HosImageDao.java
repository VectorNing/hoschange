package com.sesxh.hoschange.biz.hos.dao;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.common.data.Image;

public interface HosImageDao {
	public int insertImage(Image image) throws BaseException;
}
