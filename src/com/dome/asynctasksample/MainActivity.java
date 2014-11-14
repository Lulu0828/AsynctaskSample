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
		
		if (v == asyncTastButton) {
			/* AsyncTask的特点是任务在主线程之外运行，而回调方法是在主线程中执行，这就有效地避免了使用Handler带来的麻烦。
			 * 阅读 AsyncTask的源码可知，AsyncTask是使用java.util.concurrent 框架来管理线程以及任务的执行的，concurrent框架是一个非常 成熟，
			 * 高效的框架，经过了严格的测试。这说明AsyncTask的设计很好的解决了匿名线程存在的问题。 
			 * AsyncTask定义了三种泛型类型 Params，Progress和Result。 Params 启动任务执行的输入参数，比如HTTP请求的URL。 
			 * Progress 后台任务执行的百分比。 Result 后台执行任务最终返回的结果，比如String。 
			 * 子类必须实现抽象方法doInBackground(Params… p) ，在此方法中实现任务的执行工作，比如连接网络获取数据等。
			 * 通常还应 该实现onPostExecute(Result r)方法，因为应用程序关心的结果在此方法中返回。需要注意的是AsyncTask一定要在主线程中创 建实例。 
			 * AsyncTask的执行分为四个步骤，每一步都对应一个回调方法，需要注意的是这些方法不应该由应用程序调用，开发者需要做的 就是实现这些方法。
			 * 在任务的执行过程中，这些方法被自动调用，运行过程，如下图所示： onPreExecute() 当任务执行之前开始调用此方法，可以在这里显示进度对话框。 
			 * doInBackground(Params…) 此方法在后台线程执行，完成任务的主要工作，通常需要较长的时间。
			 * 在执行过程中可以调用 publicProgress(Progress…)来更新任务的进度。 o
			 * nProgressUpdate(Progress…) 此方法在主线程执行，用于显示任务执行的进度。 
			 * onPostExecute(Result) 此方法在主线程执行，任务执行的结果作为此方法的参数返回
			 * */
			Intent intent1 = new Intent(this, AsyncTastActivity.class);
			startActivity(intent1);
		} else if (v == threadHandlerButton) {
			/*采用Thread + Handler + Message 
			 *Handler为Android提供了一种异步消息处理机制，它包含两个队列，一个是线程列队，另一个是消息列队。
			 *使用post方法将线 程对象添加到线程队列中，使用sendMessage(Message message)将消息放入消息队列中。
			 *当向消息队列中发送消息后就立 即返回，而从消息队列中读取消息对象时会阻塞，
			 *继而回调Handler中public void handleMessage(Message msg)方法。
			 *因此 在创建Handler时应该使用匿名内部类重写该方法。如果想要这个流程一直执行的话，可以再run方法内部执行postDelay或者 post方法，
			 *再将该线程对象添加到消息队列中重复执行。想要停止线程，调用Handler对象的removeCallbacks(Runnable r)从 线程队列中移除线程对象，
			 *使线程停止执行。*/
			Intent intent2 = new Intent(this, ThreadHandlerActivity.class);
			startActivity(intent2);
		} else if (v == threadHandlerPostButton) {
			/*采用Thread + Handler + post方法
			 * 使用post方法将Runnable对象放到Handler的线程队列中，该Runnable的执行其实并未单独开启线程，而是仍然在当前Activity的UI线程中执行，
			 * Handler只是调用了Runnable对象的run方法。*/
			Intent intent3 = new Intent(this, ThreadHandlerPostActivity.class);
			startActivity(intent3);
		}
	}

}
