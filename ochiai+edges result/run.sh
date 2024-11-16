#!/bin/bash

# Path to the directory containing the input files
directory='/private/tmp/issue_30/fault-localization/gzoltar/Time/'


# Loop through each file in the directory
for bug_num in {1..27}; do
    # Run the Python script and redirect output to the output directory
    # python3 add_else.py "$directory$bug_num copy/matrix" "$directory$bug_num copy/spectra"
    # touch "./time_infor/$bug_num".txt
    python3 rank.py time Time "$bug_num"
done