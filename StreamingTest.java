package com.dbs.kafka.samples;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.dstream.DStream;

public class StreamingTest {

	public static void main(String[] args) {
		StreamingContext ssc = null;
		 ServerSocket welcomeSocket = null;
		
		try {
			//welcomeSocket = new ServerSocket(Integer.parseInt(args[1]));
			// Socket connectionSocket  =  welcomeSocket.accept();
			SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount");
			ssc = new StreamingContext(conf, new Duration(1000));
			//ReceiverInputDStream<String> lines = ssc.socketTextStream(args[0], Integer.parseInt(args[1]),StorageLevel.MEMORY_AND_DISK());
			DStream<String> lines = ssc.textFileStream("/hivestage/pgle/pgle/bip_prod/stream/test");
					
			System.out.println("===============================printing===================================");
			lines.print();
			
			

			ssc.start(); // Start the computation
			ssc.awaitTermination();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ssc.stop(true);

		}
		finally{
			try {
				welcomeSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
