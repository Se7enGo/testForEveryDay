package day710;

import java.util.ArrayList;
import java.util.Iterator;

public class MainTest {
	
	final ArrayList<String> list = getTestData();

	public static void main(String[] args) {
		MainTest ma = new MainTest();
		

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Iterator<String> it = ma.list.iterator();
				while(it.hasNext()){
					String str = it.next();
					System.out.println("删除数据 : " +str);
					if("3".equals(str)){
						it.remove();
					}
					
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0 ;i< ma.list.size();i++){
					 String str = ma.list.get(i);
					System.out.println("读取数据 : " +str);
				}
				/*for (String str : ma.list) {
					System.out.println("读取数据 : " +str);
				}*/
			}
		}).start();
		
	}
	
	public static ArrayList<String> getTestData(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		return list;
	}
}
