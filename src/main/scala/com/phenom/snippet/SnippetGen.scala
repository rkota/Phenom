package com.phenom.snippet

import scala.collection.mutable.ListBuffer
import scala.io.Source

import play.libs.Json
import com.fasterxml.jackson.databind.JsonNode
import com.phenom.snippet.utils.Utils
import edu.stanford.nlp.util.CollectionUtils
import scala.collection.JavaConversions._
import com.fasterxml.jackson.databind.ObjectMapper
import play.api.libs.json.JsObject

/**
 * @author ${user.name}
 */
object SnippetGen {
  
  var jobs_post = ListBuffer[JsonNode]()
  var jobs_desc=ListBuffer[String]()
  
  def main(args : Array[String]) {
    var inputFile = "./src/main/resources/jobs.json"
    
    for ( line <-Source.fromFile(inputFile).getLines){
      var token_list = List[String]()
      var jnode = Json.parse(line)
      var desc_html = jnode.get("description").asText()
      jobs_desc += Utils.readtext(desc_html)
      
      for(text_line <- jobs_desc){
      var l_words = Utils.lemmatize_words(text_line)
      var list = CollectionUtils.getNGrams(l_words.toList, 1, 2)
      var list_tokens = List(Set(list))
      
      jobs_post += jnode
      println(list_tokens)
      
      }
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
    
    
        
    }
    
    
    
}
