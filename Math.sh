#!/bin/bash

# Loop through bug numbers from 5 to 30
for bug_num in {5..30}; do
  # Run the job.sh command with the current bug number
  ./job.sh --project Math --bug "$bug_num" --output_dir "Math/$bug_num" --tool developer
done
