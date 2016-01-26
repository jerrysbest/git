package erp.ws.sbo.client.swing.store;

public class Tree1s {
	
	private Object[] value =
	      {  "",
			 new Object[] { "财务"},
			 new Object[] { "销售机会"},	       
	         new Object[] { "销售-应收账款",
	    		           "销售订单",
	                       "备货单",
	                       "应收贷向凭证",
	                       "质量反馈单"
	                   },        
            new Object[] { "库存",
			    		  "序列号组合",
			    		  "其它出库",
			    		  "其它入库",
			    		  "序列号入库"},  
           new Object[] { "生产",
	    		       "生产订单",	
		               "生产发货",
		               "生产收货"		             
                  }, 
          
          new Object[] { 
	    		  "物料需求计划",
	               "销售预测订单",
	               "物料需求计划向导",
	               "订单建议格式一",  
	               "订单建议格式二",
	               "订单建议格式三",
	               "质量反馈单列表"
                 },   
          
	        new Object[] {  "银行",
	    		         "批量收款单"	                       
	       },
	      
          new Object[] { "报表", 
	    		  new Object[] {"财务",
        		  "科目发生额表"
                   },
	               new Object[] {"销售",
        		  "销售日报表",
        		  "延期发货表"
                   },
                   new Object[] {"生产",
                   "生产日报表",
                   "超生产订单入库表"
                   },
	               new Object[] {"库存",
        		  "库存过账明细",
        		  "分库存量表",
        		  "序列号跟踪明细",
        		  "序列号状态"}                
                 }
	       };
	private static Tree1s t1s;
	private Tree1s()
	{
		
	}
	public static  Tree1s getHierarchy(){
	if(t1s==null)
	{
		t1s=new  Tree1s();
	}
	return t1s;
	}
	public Object[] getValue()
	{
		return value;
	}
	public void setValue(Object[] value)
	{
	 this.value=value;
	}

}
