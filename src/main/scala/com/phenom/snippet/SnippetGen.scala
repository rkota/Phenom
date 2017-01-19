package com.phenom.snippet

import scala.collection.mutable.ListBuffer
import scala.io.Source

import play.libs.Json
import com.fasterxml.jackson.databind.JsonNode
import com.phenom.snippet.utils.Utils

/**
 * @author ${user.name}
 */
object SnippetGen {
  
  var jobs_post = ListBuffer[JsonNode]()
  var jobs_desc=ListBuffer[String]()
  
  def main(args : Array[String]) {
    var inputFile = "./src/main/resources/jobs.json"
    
    for ( line <-Source.fromFile(inputFile).getLines){
      var jnode = Json.parse(line)
      jobs_post += jnode
      var desc_html = jnode.get("description").asText()
      jobs_desc += Utils.readtext(desc_html)
    }
    
   /* for(text_line <- jobs_desc){
      for(line <- text_line.split("\n")){
        if(!line.trim().isEmpty() && !"&nbsp;".equals(line.trim())){
          if(regex.eq(line)){
          println(line)
          println("---------")
          }
        }
      }
      }*/
    
    for(text_line <- jobs_desc){
      for(word <- Utils.lemmatize_words(text_line)){
        println(word)
        print("-----------------")
      }
        
    }
    }
    
}
