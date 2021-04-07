package com.example.artikolevyinf.Controller;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;


public class ConnectionHttp {

    public static String postResponse(URL url, JSONObject values){
        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            /** Parametros que se envian*/
            /** Parametros que se envian*/
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            bw.write(values.toString());
            bw.flush();
            bw.close();

//            DataOutputStream localDataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
//            localDataOutputStream.writeBytes(values.toString());
//            localDataOutputStream.flush();
//            localDataOutputStream.close();

            //Codigo de resultado
            int code = httpURLConnection.getResponseCode();

            Log.d("FFF", String.valueOf(code));

            if(code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED){
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line=reader.readLine()) != null){
                    buffer.append(line);
                }
                httpURLConnection.disconnect();
                return buffer.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String putResponse(URL url, JSONObject values){
        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            //Charset.forName("UTF-8").encode(values.toString());

            /** Parametros que se envian*/
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            bw.write(values.toString());
            bw.flush();
            bw.close();

//            DataOutputStream localDataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
//            localDataOutputStream.writeBytes(values.toString());
//            localDataOutputStream.flush();
//            localDataOutputStream.close();

            //Codigo de resultado
            int code = httpURLConnection.getResponseCode();

            Log.d("FFF", String.valueOf(code));

            if(code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED){
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line=reader.readLine()) != null){
                    buffer.append(line);
                }
                httpURLConnection.disconnect();
                return buffer.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String putImageBucket(String imageType, String id, String order, Bitmap image){
        HttpURLConnection httpURLConnection = null;

        try {
            /** Se inicializa la url con el nombre del archivo */
            String imageFinalName = imageType + id + order;
            String urlBucket = "https://objectstorage.us-ashburn-1.oraclecloud.com/p/bdSldFURKesqQSFSSFRyvXl7gLTDdkRWRS53_AZeCvUeX-K51bsBYitoOuajLBti/n/idnledxbcmox/b/bucket-20210209-1957/o/" + imageFinalName + ".jpeg";
            URL url = new URL(urlBucket);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Type", "image/jpeg; charset=UTF-8");
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            /** La imagen se convierte a un arreglo de Bytes */
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] byteImage = bos.toByteArray();

            /** Parametros que se envian*/
            DataOutputStream localDataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            localDataOutputStream.write(byteImage);
            localDataOutputStream.flush();
            localDataOutputStream.close();

            //Codigo de resultado
            int code = httpURLConnection.getResponseCode();

            Log.d("FFF", String.valueOf(code));

            if(code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED){
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line=reader.readLine()) != null){
                    buffer.append(line);
                }
                httpURLConnection.disconnect();
                String bucketURL = "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idnledxbcmox/b/bucket-20210209-1957/o/"+imageFinalName+ ".jpeg";
                Log.d("FFF", bucketURL);
                return imageFinalName;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

    public static URL getSocketInformationByKeyword(String keyword){
        URL url = null;
        try {
            url = new URL("https://socket-clarigy.herokuapp.com/api/data/" + keyword);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL getActivityInformationByKeyword(String keyword){
        URL url = null;
        try {
            url = new URL("https://zihpphd8emcltak-db202101231259.adb.us-ashburn-1.oraclecloudapps.com/ords/clarigy/catalog/" + keyword);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}
