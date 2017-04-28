package com.sesxh.hoschange.loader;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.sesxh.common.db.SQLExecutor;
import com.sesxh.common.exception.FrameException;
import com.sesxh.hoschange.base.SesframeBaseService;

@Service
public class SampleBean extends SesframeBaseService {
	public void autoRun() throws FrameException, SQLException {
		SQLExecutor sql = this.getSession().getSQLExecutor();

		System.out.println("-----------------ServletContextLoader test bean[" + this.getClass().getName() + "]");
		sql.setSQL(" select 1 from dual");
		System.out.println(sql.executeQueryRowCount());
		System.out.println("-----------------end-----------------------------------------------");
	}
}
