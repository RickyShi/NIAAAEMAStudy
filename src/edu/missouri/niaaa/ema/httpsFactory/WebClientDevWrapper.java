package edu.missouri.niaaa.ema.httpsFactory;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.util.Log;
import edu.missouri.niaaa.ema.Utilities;

public class WebClientDevWrapper extends DefaultHttpClient {
	public static HttpClient getSpecialKeyStoreClient() {
		BasicHttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		HttpProtocolParams.setUseExpectContinue(params, true);
		Log.d("SSLSchedule", "sslSocketFactory: " + (Utilities.sslSocketFactory == null));
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schReg.register(new Scheme("https", Utilities.sslSocketFactory, 443));

		ClientConnectionManager connMgr = new ThreadSafeClientConnManager(params, schReg);

		return new DefaultHttpClient(connMgr, params);
	}

	public static HttpClient getSpecialKeyStoreClient(Context context) {
		BasicHttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		HttpProtocolParams.setUseExpectContinue(params, true);
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schReg.register(new Scheme("https", CustomerSocketFactory.getSocketFactory(context), 443));

		ClientConnectionManager connMgr = new ThreadSafeClientConnManager(params, schReg);

		return new DefaultHttpClient(connMgr, params);
	}
}
