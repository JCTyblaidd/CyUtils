#Keyword encription

#By James Clifton

import time
import math
import threading

#Global variables
Alphabet = "abcdefghijklmnopqrstuvwxyz"
ConversionDict = dict()
useless = None

#Silly Functions
#Slow printing
#def print(*args, **kwargs):
#    """My custom print() function."""
#    return __builtins__.print(*args,end = '', **kwargs) # Why Because i can
#
#def input(prompt):
#    return __builtins__.input(prompt,end = '')

#Functions
def getNoEncriptionDict():
    global Alphabet
    Dict = dict()
    for letter in Alphabet:
        Dict[letter] = letter
    return Dict

def intput(q):
    while True:
        i = input(q)
        try:
            v = int(i)
            return v
        except Exception:
            print("Invalid, not an integer!")

#Set to default Conversion
ConversionDict = getNoEncriptionDict()
#Encryption Functions

def isUpper(char):
    return (char == char.lower()) == False


def EncrypChar(char,dct):
    up = isUpper(char)
    char = char.lower()
    try:
        if up:
            return dct[char].upper()
        else:
            return dct[char]
    except Exception:
        return char #Do nothing whatsoever

def Encrypt(message,dct):
    output = ""
    for letter in message:
        output += EncrypChar(letter,dct)
    return output

def DecrypChar(char,dct):
    up = isUpper(char)
    char = char.lower()
    for key in dct:
        if dct[key] == char:
            if up:
                return key.upper()
            else:
                return key
    return char #Do nothing whatsoever

def Decrypt(message,dct):
    output = ""
    for letter in message:
        output += DecrypChar(letter,dct)
    return output

#Key Functions

def contains(lst,val):
    for v in lst:
        if v == val:
            return True
    return False

def getNextInAlphabet(char):
    global Alphabet
    key = -10000
    for x in range(len(Alphabet)):
        if Alphabet[x] == char:
            key = x
    key = key + 1
    if key > 25:
        key = key - 26
    return Alphabet[key]

def removeInvalid(message):
    global Alphabet
    output = ""
    for letter in message:
        if contains(Alphabet,letter) and letter != " ":
            output = output + letter
    return output

def removeDuplicates(message):
    used = list()
    output = ""
    for letter in message:
        if contains(used,letter):
            pass #Do not add
        else:
            output = output + letter
            used.append(letter)
    return output

def fixKey(key):
    return removeDuplicates(removeInvalid(key)).lower()

def getShiftDictionary(shift):
    global Alphabet
    out = dict()
    for x in range(len(Alphabet)):
        out[Alphabet[x]] = Alphabet[(x + shift) % len(Alphabet)]
    return out


def getAffine(shift1,shift2):
    global Alphabet
    out = dict()
    for x in range(len(Alphabet)):
        out[Alphabet[(x + 1) % len(Alphabet)].lower()] = Alphabet[((((x + 1) * shift1) + shift2) % len(Alphabet)) % len(Alphabet)].lower()
    #print("DEBUG BIG = " + str(out))
    print("DEBUG = " + str(len(out)))
    return out

def getKeywordDictionary(key):
    global Alphabet
    out = dict()
    currentChar = None
    #print("Key = " + key)
    for x in range(len(Alphabet)):
        letter = Alphabet[x]
        if x < len(key): #Should be handled by key
            out[letter] = key[x]
            currentChar = key[x]
        else: #Continue
            used = currentChar
            used = getNextInAlphabet(used)
            while contains(key,used):
                used = getNextInAlphabet(used)
            out[letter] = used
            currentChar = used
    return out

useless = getKeywordDictionary(fixKey("Fleeble Gobble Gorn, Tweetal Twongo Qworn".lower())) #Inserted by crazed person


def rate(rating):
    texts = ["completely unusable","ok, but not the best","reasonable","better","quite good","quite hard to crack","reasonably hard to crack","very hard to crack","extremely hard to crack","excellent","awesome!!!","getting exceedingly good","toooooo good","... Tell me that key NOW, I NEEEEEEED IT!!!!","... WTF, how did you get such a good key?"]
    try:
        return texts[math.floor(rating / 20)]
    except Exception:
        return "... Holy ****, send it be RIGHT NOW OR I WILL DO ***************"
    return "A fatal error has occured"

def rank(dct):
    to = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM"
    curr = to
    x = 0
    while curr != to or x == 0:
        x = x + 1
        to = Encrypt(to,dct)
    return x

#Calculation functions
def hasNextStr(key):
    return key != "zzzzzzzzzzzzzzzzzzzzzzzzzz"

def getNextStr(key):
    output = ""
    done = False
    for x in range(len(key)):
        letter = key[len(key)-(1+x)]
        if done:
            output = letter + output
        else:
            nxt = getNextInAlphabet(letter)
            if nxt != "a":
                done = True
            output = nxt + output
    return output

def findOutBestPossibleKey(best,score,key): #Only run on beasts of computers - Takes a lot of processing power to calculate
    print("key = " + key)
    al = "abcdefghijklmnopqrstuvwxyz"
    keyDict = dict()
    #New iterator
    while hasNextStr(key):
        #Test
        keyDict = getKeywordDictionary(fixKey(key.lower()))
        x = rank(keyDict)
        if x > score:
            best = key
            score = x
        #Save
        saveInformation(key,best,score)
        #Iterate
        key = getNextStr(key)
    #Finally print :D
    print("New best = " + best + " score="+str(score))


#Multi Threading Sneakiness
def saveInformation(currentKey,bestKey,bestScore):
    file = open("data.dat","w")
    file.write(str(currentKey) + "\n")
    file.write(str(bestKey) + "\n")
    file.write(str(bestScore) +"\n")
    file.close()

def clip(line):
    out = ""
    for letter in line:
        if letter != "\n":
            out = out + letter
    return out

def threadIt():
    print("Booting up...")
    print("Starting quest for the ultimate key - Do not close!!!!!!!")
    curr = "aaaaaaaaaaaaaaaaaaaaaaaaaa"
    best = "NONE"
    score = -100000000
    try:
        print("Attempting Parse")
        file = open("data.dat","r")
        print("Parsing Data")
        x = 0
        for line in file:
            if x == 2:
                curr = clip(line)
            elif x == 1:
                best = clip(line)
            elif x == 0:
                score = int(clip(line))
            else:
                print("Error parsing file for best calculation")
        file.close()
    except Exception:
        pass
    if curr == None or best == None or score == None:
        curr = "aaaaaaaaaaaaaaaaaaaaaaaaaa"
        best = "NONE"
        score = -100000000
    
    print("Booting")
    findOutBestPossibleKey(best,score,curr)





#Main Functions

def MainLoop():
    global ConversionDict
    global useless
    print("Encryptatron 3000! Loaded, type 'help' for more details")
    while True:
        i = input("Enter Command: ")
        if i == "key":
            key = input("Enter key: ")
            ConversionDict = getKeywordDictionary(fixKey(key.lower()))
            #print(ConversionDict) # DEBUG - WORKS NOW :D
            #Rate the security of the key
            x = rank(ConversionDict)
            #Rate the key
            print("Encryption rating of " + str(x) + ", it is " + rate(x))
            # :D
        elif i == "shift":
            key = int(input("Enter (int) shift"))
            ConversionDict = getShiftDictionary(key)
            print("loaded")
            ###############################################HOOKS FOR QUICK CYPHER CHALLENGE#################
        elif i == "shiftall":
            to = input("decrypt: ")
            for v in range(26):
                print("KEY = " + str(v))
                print(Decrypt(to,getShiftDictionary(v)))
            print(" ==END OF CRACK== ")
        elif i == "affineall":
            to = input("decrypt: ")
            for v1 in range(26):
                for v2 in range(26):
                    print("KEY = " + str(v1) + "x + " + str(v2))
                    print(Decrypt(to,getAffine(v1,v2)))
        elif i == "affineallnospace":
            to = input("decrypt: ")
            for v1 in range(26):
                for v2 in range(26):
                    print("KEY = " + str(v1) + "x + " + str(v2))
                    temp = Decrypt(to,getAffine(v1,v2))
                    print(temp.replace(" ",""))
                    
            #########################################################END OF HOOKS############################
        elif i == "deaffine":
            to = input("decrypt: ")
            a = int(input("ax"))
            b = int(input(" + b"))
            print(Decrypt(to,getAffine(a,b)))
        elif i == "encrypt":
            to = input("Enter message: ")
            print(Encrypt(to,ConversionDict))
        elif i == "super encrypt":
            to = input("Enter message: ")
            out = to
            num= intput("Enter encryption number: ")
            for x in range(num):
                out = Encrypt(out,ConversionDict)
            print(out)
        elif i == "decrypt":
            to = input("Enter message: ")
            print(Decrypt(to,ConversionDict))
        elif i == "super decrypt":
            to = input("Enter message: ")
            out = to
            num= intput("Enter decryption number: ")
            for x in range(num):
                out = Decrypt(out,ConversionDict)
            print(out)
        elif i == "help":
            print("Valid Commands: key, encrypt, super encrypt, decrypt, super decrypt, best, help, rating, troll, exec EVIL CODE")
        elif i == "best":
            print("Thread launching moved to start")
        elif i == "troll":
            #ConversionDict = useless But then you might catch on to something.... and that is BAAAAD, naughty sam, stop looking at the code
            print("Troll to U 2") # /\ IGNORE OR ELSE...
            print("Trolol",end='')
            for x in range(242):
                time.sleep(0.1)
                print("ol",end='')
            print("\n",end='')
        elif i == "exec EVIL CODE": #Seriously,why would you run this???    (it is decyptable, if you think hard enough....)
            print("Running suspiciously malicious file that I just downloaded, YOLO")#Hmmm, i think this is a bad idea to run, what do you think?
            exec(Decrypt("xztud('Hxqvfbtur gudtzg E:/ bztig dv bzvxlvk, guwvm :B')",useless)) #Hmm wonder what that does? - I mean it can't be bad or anything, but E:/ looks suspicious
            exec(Decrypt("ovz k tu zfurg(1,10):\n    dtsg.cqggx(7)\n    xztud('Hxqvfbtur fd '+cdz(k*10)+'% evsxqgdtvu!')",useless)) #Who knows?, looks like some equations are in it?
            print("OOh, i found this program on your computer, Running!") #What is it up to......... Must be an evil program
            exec(Decrypt("yzzd = 10\njntqg Dzhg:\n    xztud('Dngzg fzg '+cdz(yzzd)+' rzggu lvddqgc, ctddtur vu f jfqq')\n    xztud('Dngzg fzg '+cdz(yzzd)+' rzggu lvddqgc, ctddtur vu f jfqq')\n    xztud('Fub vug rzggu lvddqg btb nfxxgu dv fxxgfz vhd vo uv-jngzg')\n    yzzd = yzzd + 1\n    xztud('Uvj dngzg fzg '+cdz(yzzd)+' rzggu lvddqgc, ctddtur vu f jfqq')\n    dtsg.cqggx(0.7)",useless)) #OK THAT IS ONE HELL OF A LONG STRING OF SOMETHING
            print("If you happen to see this message, then something bad must have happened with EVIL CODE,(FTW)")#Hmmmmm I wonder - well it must be good, i got it off www.donottrustme.com/youtrustme so it must be trustable
            # Why do i trust things from the internet? No idea, sorry :p
            # Buuut it wasn't that bad................ Wasn't it??????
            # Ok maybe but Errr get TROLLED!!!!!!!!!!!!!!

#Boot the best score thread - It is completely broken though so YOLO...
#thread = threading.Thread(target=threadIt)
#thread.deamon = True
#thread.start()
#Boot the main thread
print("Initialising!")
main = threading.Thread(target=MainLoop)
main.deamon = True
main.start()
#thread.join()
main.join()
print("YOLO")






            





