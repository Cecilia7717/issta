# Use of Control Flow Graphs for Fault Localization

# code
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

# data_output
