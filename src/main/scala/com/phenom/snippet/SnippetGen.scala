package com.phenom.snippet

import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

import scala.collection.JavaConversions.seqAsJavaList
import scala.collection.mutable.ListBuffer
import scala.io.Source

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.phenom.snippet.summerization.DocumentFrequencyCounter
import com.phenom.snippet.summerization.Summarizer
import com.phenom.snippet.utils.Utils

import play.libs.Json
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * @author ${user.name}
 */
object SnippetGen {
  
  var jobs_posts = ListBuffer[JsonNode]()
  var jobs_desc_arr=ListBuffer[String]()
  var keywords_total = List[String]()
  var key_words = ListBuffer[String]()
  
  def main(args : Array[String]) {
    var inputFile = "./src/main/resources/jobs.json"
    
    for ( line <-Source.fromFile(inputFile).getLines){
      var token_list = List[String]()
      var jnode = Json.parse(line)
      var desc_html = jnode.get("description").asText()
      var jobs_desc = Utils.readtext(desc_html).replaceAll("&nbsp", " ")
      jobs_desc_arr += jobs_desc
      jnode.asInstanceOf[ObjectNode].put("jobs_desc", jobs_desc)
      
     /* for(text_line <- jobs_desc){
        var l_words = Utils.lemmatize_words(text_line)
        var list = CollectionUtils.getNGrams(l_words.toList, 1, 2)
        var list_tokens = list.distinct
        for(list_tok <- list){
          keywords_total.addAll(list_tok)
        }
        
       
        println(list_tokens)
        println("-------------------")
        
      }*/
       jobs_posts += jnode
      
    }
     var dfCounter = new DocumentFrequencyCounter
     var dfCounter_file = dfCounter.generateDFCount(jobs_desc_arr.toList)
     var summarizer = new Summarizer(dfCounter_file)
     val mapper = new ObjectMapper()
     val pw = new PrintWriter(new FileOutputStream(new File("./src/main/resources/jobs_summery.txt"),true))
     for(jsnode <- jobs_posts){
      // println(jsnode.get("jobs_desc").asText())
      var sum = summarizer.summarize(jsnode.get("jobs_desc").asText(), 2)
      if(!sum.trim().isEmpty()){
       jsnode.asInstanceOf[ObjectNode].put("summery", sum).remove("jobs_desc");
        pw.append( mapper.writeValueAsString(jsnode))
        pw.append("\n")
      }
     }
      
    
    }
    
    
    
}
