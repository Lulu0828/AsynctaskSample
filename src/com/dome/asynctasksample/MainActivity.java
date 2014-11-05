package com.dome.asynctasksample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	Button asyncTastButton;
	Button threadHandlerButton;
	Button threadHandlerPostButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		asyncTastButton = (Button)findViewById(R.id.AsyncTast);
		threadHandlerButton = (Button)findViewById(R.id.ThreadHandler);
		threadHandlerPostButton = (Button)findViewById(R.id.ThreadHandlerPost);
		
		asyncTastButton.setOnClickListener(this);
		threadHandlerButton.setOnClickListener(this);
		threadHandlerPostButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		if (v == asyncTastButton) {
			/* AsyncTask���ص������������߳�֮�����У����ص������������߳���ִ�У������Ч�ر�����ʹ��Handler�������鷳��
			 * �Ķ� AsyncTask��Դ���֪��AsyncTask��ʹ��java.util.concurrent ����������߳��Լ������ִ�еģ�concurrent�����һ���ǳ� ���죬
			 * ��Ч�Ŀ�ܣ��������ϸ�Ĳ��ԡ���˵��AsyncTask����ƺܺõĽ���������̴߳��ڵ����⡣ 
			 * AsyncTask���������ַ������� Params��Progress��Result�� Params ��������ִ�е��������������HTTP�����URL�� 
			 * Progress ��̨����ִ�еİٷֱȡ� Result ��ִ̨���������շ��صĽ��������String�� 
			 * �������ʵ�ֳ��󷽷�doInBackground(Params�� p) ���ڴ˷�����ʵ�������ִ�й������������������ȡ���ݵȡ�
			 * ͨ����Ӧ ��ʵ��onPostExecute(Result r)��������ΪӦ�ó�����ĵĽ���ڴ˷����з��ء���Ҫע�����AsyncTaskһ��Ҫ�����߳��д� ��ʵ���� 
			 * AsyncTask��ִ�з�Ϊ�ĸ����裬ÿһ������Ӧһ���ص���������Ҫע�������Щ������Ӧ����Ӧ�ó�����ã���������Ҫ���� ����ʵ����Щ������
			 * �������ִ�й����У���Щ�������Զ����ã����й��̣�����ͼ��ʾ�� onPreExecute() ������ִ��֮ǰ��ʼ���ô˷�����������������ʾ���ȶԻ��� 
			 * doInBackground(Params��) �˷����ں�̨�߳�ִ�У�����������Ҫ������ͨ����Ҫ�ϳ���ʱ�䡣
			 * ��ִ�й����п��Ե��� publicProgress(Progress��)����������Ľ��ȡ� o
			 * nProgressUpdate(Progress��) �˷��������߳�ִ�У�������ʾ����ִ�еĽ��ȡ� 
			 * onPostExecute(Result) �˷��������߳�ִ�У�����ִ�еĽ����Ϊ�˷����Ĳ�������
			 * */
			Intent intent1 = new Intent(this, AsyncTastActivity.class);
			startActivity(intent1);
		} else if (v == threadHandlerButton) {
			/*����Thread + Handler + Message 
			 *HandlerΪAndroid�ṩ��һ���첽��Ϣ������ƣ��������������У�һ�����߳��жӣ���һ������Ϣ�жӡ�
			 *ʹ��post�������� �̶�����ӵ��̶߳����У�ʹ��sendMessage(Message message)����Ϣ������Ϣ�����С�
			 *������Ϣ�����з�����Ϣ����� �����أ�������Ϣ�����ж�ȡ��Ϣ����ʱ��������
			 *�̶��ص�Handler��public void handleMessage(Message msg)������
			 *��� �ڴ���HandlerʱӦ��ʹ�������ڲ�����д�÷����������Ҫ�������һֱִ�еĻ���������run�����ڲ�ִ��postDelay���� post������
			 *�ٽ����̶߳�����ӵ���Ϣ�������ظ�ִ�С���Ҫֹͣ�̣߳�����Handler�����removeCallbacks(Runnable r)�� �̶߳������Ƴ��̶߳���
			 *ʹ�߳�ִֹͣ�С�*/
			Intent intent2 = new Intent(this, ThreadHandlerActivity.class);
			startActivity(intent2);
		} else if (v == threadHandlerPostButton) {
			/*����Thread + Handler + post����
			 * ʹ��post������Runnable����ŵ�Handler���̶߳����У���Runnable��ִ����ʵ��δ���������̣߳�������Ȼ�ڵ�ǰActivity��UI�߳���ִ�У�
			 * Handlerֻ�ǵ�����Runnable�����run������*/
			Intent intent3 = new Intent(this, ThreadHandlerPostActivity.class);
			startActivity(intent3);
		}
	}

}
