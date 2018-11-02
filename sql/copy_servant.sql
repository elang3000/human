   /*---------------------------------------------------------------------------
   || 业务环节 ：复制公务员信息
   || 过程名称 ：COPY_SERVANT
   || 功能描述 ：复制公务员基本信息和子集信息，返回复制出的人员信息id（SERVANTNEWID）
   || 使用场合 ：
   || 其它说明 ：
   || 使用要求 ：
   || 作    者 ：汤子杨
   || 完成日期 ：2018-09-18
   ||---------------------------------------------------------------------------
   ||                                 修改记录
   ||---------------------------------------------------------------------------
   || 修 改 人 ：×××                        修改日期 ：YYYY-MM-DD
   || 修改描述 ：
   ||-------------------------------------------------------------------------*/
	CREATE OR REPLACE PROCEDURE "COPY_SERVANT"(SERVANTID        IN    VARCHAR2      ,  --  需要复制公务员信息id
						SERVANTNEWID       OUT   VARCHAR2    ,	--  复制出的人员信息id
                          prm_AppCode       OUT   VARCHAR2    ,                                 
                          prm_ErrorMsg      OUT   VARCHAR2 )
AS
      /*变量声明*/
	  MYCOLUMNS VARCHAR2(1500); --临时存放子集表除去ID和SERVANT_ID的字段名,insert into select 后面
	  MYCOLUMNS2 VARCHAR2(1500); --临时存放子集表除去ID和SERVANT_ID的字段名，insert into 后面
	  TABLENAME VARCHAR2(100);--存放循环生成的子集信息表名
	  INSERTSQL VARCHAR2(3000);--存放插入数据的sql语句
	  i number;
BEGIN
	  i:=2;--从A02表开始循环生成插入语句
	  SERVANTNEWID:=sys_guid();
	  
	  SELECT wm_concat(NAME) INTO MYCOLUMNS FROM syscolumns WHERE id in(SELECT id FROM sysobjects WHERE (name = 'A01')) and name not in('ID');

	  set MYCOLUMNS2 = CONCAT(MYCOLUMNS,',id');

	  set MYCOLUMNS = CONCAT(MYCOLUMNS,',''',SERVANTNEWID,'''');
	  
	  set INSERTSQL = CONCAT('insert into A01 (',MYCOLUMNS2,') select ',MYCOLUMNS,' from A01 t where ID=''',SERVANTID,'''');
      
      EXECUTE immediate INSERTSQL;
      
      while i < 100 LOOP 
		begin 
			TABLENAME:=CONCAT('A',lpad(i,2,'0'));--表名拼接，个位数的情况下左边拼接一个0，如，2变成02,11这种两位数的不变
			SELECT wm_concat(NAME) INTO MYCOLUMNS FROM syscolumns WHERE id in(SELECT id FROM sysobjects WHERE (name = TABLENAME)) and name not in('ID','SERVANT_ID');
			IF MYCOLUMNS IS NOT NULL THEN
		          set MYCOLUMNS2 = CONCAT(MYCOLUMNS,',id,SERVANT_ID');
	
				  set MYCOLUMNS = CONCAT(MYCOLUMNS,',GET_UUID(),''',SERVANTNEWID,'''');
				  
				  set INSERTSQL = CONCAT('insert into ',TABLENAME,' (',MYCOLUMNS2,') select ',MYCOLUMNS,' from ',TABLENAME,' t where SERVANT_ID=''',SERVANTID,'''');
			      
			      EXECUTE immediate INSERTSQL;
			      
		    END IF ;
		i:= i + 1; 
		end; 
	  end LOOP;

      COMMIT;
		/*成功处理*/
      <<label_Ok>>

      
      /*给返回参数赋值*/
      Prm_Appcode  := 'OK';
      Prm_Errormsg := '';
      RETURN;



   EXCEPTION
      WHEN OTHERS THEN
        /*关闭打开的游标*/
        Prm_Appcode  := 'ERROR';
        Prm_Errormsg := '获取数据错误'
                       ||',出错原因:'||SQLERRM
                       ||'错误行数:'||DBMS_UTILITY.FORMAT_ERROR_BACKTRACE();  --错误代码行数
        RETURN;
 
END COPY_SERVANT;