package org.wwx.cglib.source.test;

import net.sf.cglib.beans.ImmutableBean;


class SampleBean {
    private String value;

    public SampleBean() {
    }

    public SampleBean(String value) {
        this.value = value;
    }
    public String geValue() {//注意方法名ge
        return this.value;
    }
    public String getValue() {//正常get方法
        return this.value;
    }
    public String getttttt() {//石乐志的get方法
        return this.value;
    }
    public void setValue(String value) {//正常set方法
        this.value = value;
    }
    public void settValue(String value) {//石乐志set方法
        
    }
    public void seValue(String value){//注意方法名se
    	this.value = value;
    }
}
public class CglibTest1 {

	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		 SampleBean bean = new SampleBean("ww");
	     SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean); //创建不可变类
	     
		 System.err.println(immutableBean.getValue());//获得ww
		 immutableBean.seValue("22");//不报错
		 //immutableBean.settValue("");//报错
		 //immutableBean.setValue("");//报错
		 String s = immutableBean.geValue();
		 System.err.println(s);//获得22
		 String str = immutableBean.getValue();
		 System.err.println(str);//获得ww
		 String str1 = immutableBean.getttttt();
		 System.err.println(str1);//获得ww
		
	}

}
