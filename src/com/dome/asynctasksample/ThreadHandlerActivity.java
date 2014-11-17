package com.dome.asynctasksample;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

/*HandlerΪAndroid�ṩ��һ���첽��Ϣ������ƣ��������������У�һ�����߳��жӣ���һ������Ϣ�жӡ�ʹ��post�������� �̶�����ӵ��̶߳����У�
 * ʹ��sendMessage(Message message)����Ϣ������Ϣ�����С�������Ϣ�����з�����Ϣ����� �����أ�������Ϣ�����ж�ȡ��Ϣ����ʱ��������
 * �̶��ص�Handler��public void handleMessage(Message msg)��������� �ڴ���HandlerʱӦ��ʹ�������ڲ�����д�÷�����
 * �����Ҫ�������һֱִ�еĻ���������run�����ڲ�ִ��postDelay���� post�������ٽ����̶߳�����ӵ���Ϣ�������ظ�ִ�С�
 * ��Ҫֹͣ�̣߳�����Handler�����removeCallbacks(Runnable r)�� �̶߳������Ƴ��̶߳���ʹ�߳�ִֹͣ�С�
 */
public class ThreadHandlerActivity extends Activity{

	private ListView listView;
	
	private List<String> urlList;
	private ArrayList<HashMap<String, Object>> itemList;
	
	private ImageAdapter listItemAdapter;
	
	private Handler handler;
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		listView = (ListView)findViewById(R.id.listview);

        urlList = new ArrayList<String>();
        urlList.add("http://www.baidu.com/img/baidu_sylogo1.gif");
        urlList.add("http://y2.ifengimg.com/2012/06/24/23063562.gif");
        urlList.add("http://himg2.huanqiu.com/statics/images/index/logo.png");
        
        itemList = new ArrayList<>();
        
        listItemAdapter = new ImageAdapter(this, itemList);
        listView.setAdapter(listItemAdapter);
        
        handler = new Handler(){

			@SuppressWarnings("unchecked")
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				HashMap<String, Object> map = (HashMap<String, Object>)msg.obj;
				itemList.add(map);
				listItemAdapter.notifyDataSetChanged();
			}
        	
        };
        
        for (final String urlString : urlList) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						URL url = new URL(urlString);
						Drawable drawable = Drawable.createFromStream(url.openStream(), "src");
						HashMap<String, Object> table = new HashMap<>();
						table.put("ItemImage", drawable);
						Message msg = new Message();
						msg.obj = table;
						msg.setTarget(handler);
						handler.sendMessage(msg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

}
