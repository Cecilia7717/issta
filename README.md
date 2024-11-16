# Use of Control Flow Graphs with Edges Consideration for Fault Localization

# Tarantula+Edges code
## functional code: 
Containing all codes that are used for evaluation of the performance.
### Ranking inside of each mutation
`DetermineLikelihoods.java` returns the ranking of each code line by there score of suspiciousness for all mutations for a source code testing. To run this, you need to first adjust the direction of file that you want to work on _(line 8 and line 20)_. The folders’ directions are usually named as **nameOfSourceCode_test_results/**. 

This file can be controlled to add the consideration of edges or not by changing the callee function between `LikelihoodForOneMutant.java` and `LikelihoodForOneMutantIfElse.java` on  _(line 20 - line 22)_.


### Return ranking statistics
`DetermineRanking.java` and `DetermineRankingIfElse.java` returns the number of mutations whose buggy line are ranked as top 1, top 2, top 3, and top 4 or below. To run this, you need to include the name of the txt file we generated from `DetermineLikelihoods.java`. Both of them will generate two cases, one with the consideration of edges, one without.

`DetermineRankingIfElse.java` only includes those mutations that the bug is inside of conditional statement, which the result is often stored in the txt file in `data_output/ranking.txt/nameOfSourceCode_test_totalResultsWithIfElseConsider.txt`. `DetermineLikelihoods.java` generates all mutation as long as there is at least one passed case and at least one failed case,  which the result is often stored in the txt file in `data_output/ranking.txt/nameOfSourceCode_test_totalResults.txt`.

### Test set-union
`FailingTest.java` and `FailingTestStat.java` are used for generating the set-union approach. You can run `FailingTest.java` with an argument of the name of the file inside **data_output/sequential\ path\ result/**. This will print out the buggy line number, and the set of suspicious code line with both the edge consideration included and excluded.

Then, in order to know the statistics information for this, you can run `FailingTestStat.java` with the output file from above as argument. You can know the number of empty set, the number go the non-empty set which contains the buggy line or does contain the buggy line, and also the average number of line you need to examine before locating the buggy line.

## source code:
This part contains all methods that we add mutations and examine. You will have the corresponding line number computed when you execute a line while running it.

## source code test:
This collects all the java file that compute the right result for each method, i.e. they don’t have any fault inside. You can have either _Pass_ or _Fail_ output.

# Tarantula+Edges data_output
This folder contains all the output data for testing the Tarantula+Edges appraoch in five methods from the Apache Commons Mathematics Library: hyperbolic sine (sinh) and cosine (cosh); power (pow); logarithm (log); prime; and an
iterative implementation of Euclid’s greatest common divisor (gcd) algorithm, which were implemented in Java. 

## sequential path result
This contains the execuation information for each codeline in different mutations for testing different approach.

## ranking
This includes the ranking information after we knowing the result of each test cases (Pass or Fail) and the executation information in each line, we then can compute the suspiciousness of each codeline using both Tarantula appraoch and the Tarantula+Edges approach. 

We can find the ranking results, where each codeline has been ranked according to the suspiciousness score computed. Where `<method>_test_totalResults.txt` includes the result without edge consideration; those `<method>_test_totalResultsWithIfElseConsider.txt` includes the result with edge consideration.

## line number needs to examine
With the information from the *ranking* part, we can count the number of the codeline that we need to investigate until reaching the faulty line.

## suspicious line within each buggy version
This foloder includes the result of using the Set-union approach. For each text file inside of this folder. This includes the set results that contains potentially faulty lines. We compute the result with edge consideration and without edge consideration, and the number of lines contained in the set.

## summary.xlsx
This inlcudes a result summary from above results.

# ochiai-Edges implementation
We comapred the effectiveness of ochiai+Edges approach with the orginal ochiai appraoch in 83 buggy versions (71 valid buggy version) total in three different
libraries from Defects4J: Commons Math, JFreeChart, and Joda-Time. In order to have the line execution information so that we can compute those Spectrum-Based Fault Localization, we use GZoltar to compute this information for each line. 

## initialization
First, here are the installations we need to do:
- JDK8 https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Fault-localization-data https://bitbucket.org/rjust/fault-localization-data/downloads/
- Defects4j-2.0.0 https://github.com/rjust/defects4j/releases/tag/v2.0.0
- Defects4j-repos https://defects4j.org/downloads/defects4j-repos.zip
- DBI-1.643 https://cpan.metacpan.org/authors/id/T/TI/TIMB/DBI-1.643.tar.gz
Now, to set the enviroment, we could apply env.txt. Or we could:
```
tar -zxvf jdk-8u281-linux-x64.tar.gz
# adjust jdk enviroment
sudo vim /etc/profile
export JAVA_HOME=/home/ubuntu/jdk1.8.0_281
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
source /etc/profile

# install package
sudo apt-get install subversion
sudo apt-get install cpanminus
sudo apt-get install maven
```

