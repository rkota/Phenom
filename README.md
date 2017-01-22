# Snippet Generation
Snippet generation is the task of extracting important keywords from various documents and
processing them to a structured knowledge base. These extracted words or keywords will
determine important facts from the document. You are given the Jobs data (described in input
section). The task is to build the model to get snippets.

**Part 1:** : For example, one way of snippet generation is by using skills from jobs. There can be a
large number of skills mentioned in a job but only few of them are relevant. So your model
should be able to decide which skills you should highlight and which you should ignore. Again
there can be multiple ways of snippet generation. Your method should be able to rank them
appropriately.

**Part 2 :** Next step is to build summarization model. This model should give the Job summary,
meaning the summary should contain the meaning and knowledge about the Job. It must be at
least 50% less than the original Job description.

**Input:**
File contains one JSON per line. Each json object describes a job which contains the following
fields : title, description, id, posted date, category, location.
[Input File](https://github.com/rkota/Phenom/blob/master/src/main/resources/jobs.json)


**Output:**
[Output File](https://github.com/rkota/Phenom/blob/master/src/main/resources/jobs_summery.txt)

# Requirements

* Scala 2.11.8
* stanford NLP libraries

# Run command from interpreter
 SnippetGen.scala


