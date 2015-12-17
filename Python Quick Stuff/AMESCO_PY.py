#amesco bruteforce
import math





textin = input("TEXT: ")
sizein = int(input("LEN: "))

textin = textin.replace(" ","")

def decryptAMESCO(text,size,key,single):
    #SPLIT
    splits = list()

    double = single;
    loc = 0;

    while loc < len(text):
        if double:
            temp1 = text[loc]
            temp2 = text[loc + 1]
            loc = loc + 2
            temp3 = str();
            temp3 = temp3 + temp1 + temp2;
            splits.append(temp3)
            double = False
        else:
            splits.append(text[loc])
            loc = loc + 1
            double = True
    #SPLIT INTO len number of strs
    
    
    
    

    print(splits)

decryptAMESCO(textin,sizein)
