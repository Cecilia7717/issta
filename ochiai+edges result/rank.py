import sys
def calculate_averages(file_a, file_b):
    # Read the list of names from file_a
    with open(file_a, 'r') as fa:
        names = [line.strip() for line in fa]

    # Dictionary to store sums and counts for each name
    name_data = {name: {'sum': 0, 'count': 0, 'number': 0} for name in names}

    # Process file_b to find each name's occurrences and accumulate the integers
    with open(file_b, 'r') as fb:
        i = 0
        for line in fb:
            # Split the line to extract the integer part and the rest of the line
            try:
                int_part, text_part = line.split(':', 1)
                integer_value = int(int_part.strip())
                i+=1
            except ValueError:
                # Skip any lines that don't match the expected format
                continue

            # Check if any name from file_a appears in this line from file_b
            for name in names:
                if name in text_part:
                    name_data[name]['sum'] += integer_value
                    name_data[name]['number'] += i
                    #print(integer_value)
                    name_data[name]['count'] += 1
    """for name in names:
        print(name_data[name]['sum'])
        print(name_data[name]['number'])
        print(name_data[name]['count'])
        break"""

    # Calculate averages and return the results
    averages = {name: {'rank' : 0, 'percentage' : 0} for name in name_data}
    for name, data in name_data.items():
        if data['count'] > 0:
            averages[name]['rank'] = data['sum'] / data['count']
        else:
            averages[name]['rank'] = None  # Or any other indication that the name wasn't found
        if data['sum'] > 0:
            averages[name]['percentage'] = data['number'] / i
        else:
            averages[name]['percentage'] = None
    '''for name in name_data:
        if averages[name]['rank']!= None:
            print(f"rank: {averages[name]['rank']}")
    for name in name_data:
        if averages[name]['percentage']!= None:
            print(f"percentage: {averages[name]['percentage']}")'''
    
    return averages, name_data

def mean(avg, avg1, name_data):
    best_rank = 10000000
    best_rank_1 = 10000000
    mean_rank = 0
    mean_rank_1 = 0
    best_percentage = 10000000
    best_percentage_1 = 10000000
    mean_percentage = 0
    mean_percentage_1 = 0
    valid_case = 0
    valid_case_1 = 0
    for name in name_data:
        if (avg[name]['rank'] != None) & (avg[name]['percentage'] != None):
            mean_rank += avg[name]['rank']
            mean_percentage += avg[name]['percentage']
            if avg[name]['rank'] < best_rank:
                best_rank = avg[name]['rank']
            if avg[name]['percentage'] < best_percentage:
                best_percentage = avg[name]['percentage']
            valid_case+=1
        if (avg1[name]['rank'] != None) & (avg1[name]['percentage'] != None):
            mean_rank_1 += avg1[name]['rank']
            mean_percentage_1 += avg1[name]['percentage']
            valid_case_1+=1
            if avg1[name]['rank'] < best_rank_1:
                best_rank_1 = avg1[name]['rank']
            if avg1[name]['percentage'] < best_percentage_1:
                best_percentage_1 = avg1[name]['percentage']
    #if valid_case == valid_case_1:
    #    print("right")
    if (valid_case != 0) & (valid_case_1!=0):
        mean_rank_final = mean_rank / valid_case
        mean_percentage_final = mean_percentage / valid_case
        mean_rank_1_final = mean_rank_1 / valid_case_1
        mean_percentage_1_final = mean_percentage_1 / valid_case_1
    else:
        mean_rank_final = -1
        mean_percentage_final = -1
        mean_rank_1_final = -1
        mean_percentage_1_final = -1

    return mean_rank_final, mean_rank_1_final, best_rank, best_rank_1, mean_percentage_final, mean_percentage_1_final, best_percentage, best_percentage_1



def compare(avg, avg1, name_data, number):
    for name in name_data:
        if (avg[name]['rank'] != None) & (avg1[name]['rank'] != None):
            if avg[name]['rank'] > avg1[name]['rank']:
                print(f"ranking is better")
                break
    for name in name_data:
        if (avg[name]['percentage'] != None) & (avg1[name]['percentage'] != None):
            if avg[name]['percentage'] > avg1[name]['percentage']:
                print(f"percentage is better")
                break
    for name in name_data:
        if (avg[name]['rank'] != None)&(avg1[name]['rank'] != None):
            if avg[name]['rank'] < avg1[name]['rank']:
                print(f"ranking is worse")
                break
    for name in name_data:
        if (avg[name]['percentage'] != None)&(avg1[name]['percentage'] != None):
            if avg[name]['percentage'] < avg1[name]['percentage']:
                print(f"percentage is worse")
                break

name = sys.argv[1]
Name = sys.argv[2]
number = sys.argv[3]
# Example usage:
file_a = f'./{name}_infor/{number}.txt'  # Replace with the path to file a
file_b = f'./{Name}/{number}.txt'  # Replace with the path to file b
file_c = f'./{Name}_1/{number}.txt'  # Replace with the path to file b
# print(f"without edges:") 
average, name_data = calculate_averages(file_a, file_b)
# print(f"with edges:")
average_2, name_data = calculate_averages(file_a, file_c)
print(f"{number}:")
compare(average,average_2, name_data, number)
print(mean(average, average_2, name_data))