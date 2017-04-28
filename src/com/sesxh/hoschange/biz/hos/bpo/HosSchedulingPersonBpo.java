package com.sesxh.hoschange.biz.hos.bpo;

import java.util.List;

import com.sesxh.base.BaseException;
import com.sesxh.hoschange.biz.hos.entity.HosScheduling;
import com.sesxh.hoschange.biz.hos.entity.HosSchedulingPerson;

public interface HosSchedulingPersonBpo {
	/**
	 * 新增手术室排班人员
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public void insertHosSchedulingPerson(HosScheduling hosScheduling) throws BaseException;

	/**
	 * 根据手术排班id删除手术室排班的所有人员
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosSchedulingPersonByOperschedulid(String operschedulid) throws BaseException;

	/**
	 * 根据手术排班id和手术人员删除固定的人员
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public int deleteHosSchedulingPersonByOperschedulid(String operschedulid, Integer userid) throws BaseException;

	/**
	 * 根据手术排班人员查看手术室排班人员
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public List<HosSchedulingPerson> selectHosSchedulingPersonByOperschedulid(String operschedulid)
			throws BaseException;

	/**
	 * 更新手术排班记录
	 * 
	 * @param hosScheduling
	 * @return
	 * @throws BaseException
	 */
	public int updateHosSchedulingPerson(HosScheduling hosScheduling) throws BaseException;
}
