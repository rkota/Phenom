package com.phenom.snippet.utils

import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.jsoup.nodes.Document
import scala.collection.mutable.ListBuffer
import edu.stanford.nlp.util.CoreMap
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import java.util.Properties
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import scala.collection.JavaConversions._
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation
import edu.stanford.nlp.util.CollectionUtils



object Utils {
  def readtext(html: String): String = {
    var document = Jsoup.parse(html);
    document.outputSettings(new Document.OutputSettings().prettyPrint(false)); //makes html() preserve linebreaks and spacing
    document.select("br").append("\\n");
    document.select("p").prepend("\\n\\n");
    var s = document.html().replaceAll("\\\\n", "\n");
    Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
  }
  
  def lemmatize_words(documentText : String) : ListBuffer[String] = {
    var lemmas = ListBuffer[String]()
    var document = new Annotation(documentText);
    var props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");

        // StanfordCoreNLP loads a lot of models, so you probably
        // only want to do this once per execution
        var pipeline = new StanfordCoreNLP(props);
        // run all Annotators on this text
        pipeline.annotate(document);

        // Iterate over all of the sentences found
        var sentences = document.get(classOf[SentencesAnnotation]);
        for(sentence <- sentences) {
            // Iterate over all tokens in a sentence
            for (token <- sentence.get(classOf[TokensAnnotation])) {
                // Retrieve and add the lemma for each word into the list of lemmas
                lemmas += token.get(classOf[LemmaAnnotation]);
            }
        }
    lemmas
    
    
  }
 /* def summarize( document:String, numSentences:Int):String= {
    var annotation = pipeline.process(document);
    var sentences = annotation.get(new CoreAnnotations.SentencesAnnotation)

    var tfs = getTermFrequencies(sentences);
    sentences = rankSentences(sentences, tfs);

    var ret = new StringBuilder();
    for ( i <-  0 to numSentences) {
      ret.append(sentences.get(i));
      ret.append(" ");
    }

    ret.toString();
  }*/
  
  def getNgrams(words : Array[String]) : ListBuffer[String] = {
    var ngram = ListBuffer[String]()
    //var words =  CollectionUtils.getNGrams(words, 1, 2)
    ngram
  }
  
}