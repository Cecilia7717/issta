def process_file(filename):
    total_valid = 0
    total_invalid = 0
    property_counts = [0, 0, 0, 0]  # For properties 1 through 4
    better_percentage = 0
    better_ranking = 0
    total = 0
    total_1 = 0
    total_2 = 0
    total_3 = 0
    with open(filename, 'r') as file:
        for line in file:
            line = line.strip()
            if line == "percentage is better":
                better_percentage+=1
            if line == "ranking is better":
                better_ranking += 1
            if not line.startswith('('):
                continue  # Ignore lines that do not start with '('

            # Split the line by commas
            parts = line.strip().split(', ')
            # print(parts[1:-1])            
            # Check if the line has the expected number of elements (at least 10 for all properties)
            if len(parts) < 10:
                continue

            # Check if the second index is "-1", marking it as invalid
            if parts[1].strip() == "-1":
                total_invalid += 1
                continue  # Skip to the next line

            # Increment valid count if it's not invalid
            total_valid += 1
            # print(part for part in parts[2:-1])
            # Convert relevant indices to floats (from 2nd to the second-last index)
            try:
                float_values = [float(part) for part in parts[1:-1]]
            except ValueError:
                # If conversion fails, skip this line
                total_invalid += 1
                continue
            total += float_values[0]
            total_1 += float_values[1]
            total_2 += float_values[4]
            total_3 += float_values[5]
            # Evaluate each property condition
            if float_values[0] > float_values[1]:  # Property 1
                property_counts[0] += 1
            if float_values[2] > float_values[3]:  # Property 2
                property_counts[1] += 1
            if float_values[4] > float_values[5]:  # Property 3
                property_counts[2] += 1
            if float_values[6] > float_values[7]:  # Property 4
                property_counts[3] += 1

    return {
        "mean rank ": total_2/total_valid,
        "mean rank 1": total_3/total_valid,
        "total_valid_lines": total_valid,
        "total_invalid_lines": total_invalid,
        "better ranking": better_ranking,
        "better percentage": better_percentage,
        "better mean ranking": property_counts[0],
        "better best ranking": property_counts[1],
        "better mean percentage": property_counts[2],
        "better best percentage": property_counts[3],
    }

# Example usage
filename = 'math_result.txt'
# filename = 'chart_result.txt'
# filename = 'time_result.txt'
result = process_file(filename)
print(result)
# filename = 'math_result.txt'
filename = 'chart_result.txt'
# filename = 'time_result.txt'
result = process_file(filename)
print(result)
# filename = 'math_result.txt'
# filename = 'chart_result.txt'
filename = 'time_result.txt'
result = process_file(filename)
print(result)
# math: {'total_valid_lines': 25, 'total_invalid_lines': 5, 'better ranking': 0, 'better percentage': 5, 'better mean ranking': 0, 'better best ranking': 0, 'better mean percentage': 5, 'better best percentage': 0}
# chart: {'total_valid_lines': 22, 'total_invalid_lines': 4, 'better ranking': 3, 'better percentage': 3, 'better mean ranking': 3, 'better best ranking': 1, 'better mean percentage': 3, 'better best percentage': 1}
# time: {'total_valid_lines': 24, 'total_invalid_lines': 3, 'better ranking': 3, 'better percentage': 6, 'better mean ranking': 3, 'better best ranking': 2, 'better mean percentage': 4, 'better best percentage': 2}

# {'mean rank ': 0.10941268979930363, 'mean rank 1': 0.10962910799669812, 'total_valid_lines': 25, 'total_invalid_lines': 5, 'better ranking': 0, 'better percentage': 5, 'better mean ranking': 0, 'better best ranking': 0, 'better mean percentage': 5, 'better best percentage': 0}
# {'mean rank ': 0.14363833549354407, 'mean rank 1': 0.14360456634022514, 'total_valid_lines': 22, 'total_invalid_lines': 4, 'better ranking': 3, 'better percentage': 3, 'better mean ranking': 3, 'better best ranking': 1, 'better mean percentage': 3, 'better best percentage': 1}
# {'mean rank ': 0.27119012090070727, 'mean rank 1': 0.2712818274035713, 'total_valid_lines': 24, 'total_invalid_lines': 3, 'better ranking': 3, 'better percentage': 6, 'better mean ranking': 3, 'better best ranking': 2, 'better mean percentage': 4, 'better best percentage': 2}