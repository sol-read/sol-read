## This script was a working system for extracting graphical data from a series of .txt files
## that measured the light spectra from various paint samples as part of my Master's project.


########################################################################################
#_____Importing libraries and declaring things that won't change from time to time_____#
########################################################################################

import numpy as np

preamble = 14                 
pixels = 2048
ymax = 2

dark_count = [0 for i in range(pixels)]
light_count = [0 for i in range(pixels)] 
intensity = [0 for i in range(pixels)]
energy = [0 for i in range(pixels)]


##############################################################################################
#_____Declaring items that will change from time to time, but not within one set of data_____#  
##############################################################################################           


date = "201203"
direct = "/Users/solread/Masters Project/Intensity Plotter/Python Project/" + "data_" + date + "/"
orig_fname = direct + "Original Data/" + date + "AA.txt"     #Allows for quicker acquisition of wavelength

darks = ["EB","FB"]             #Makes averaging of dark/light counts easier
lights = ["EA","FA"]
Dark_Raw = [[0 for i in range(pixels)] for j in range(len(darks))]
Light_Raw = [[0 for i in range(pixels)] for j in range(len(lights))]

wavelength = np.loadtxt(orig_fname,skiprows=preamble)[:,0]  #Loads wavelength into array

for i in range(pixels):     #Turns wavelength to energy scale in eV)
    energy[i] = 1242.375/wavelength[i]
    
##############################################################################
#____________Declaring functions for use in the main body of code____________#
##############################################################################


def Averaging():                #Extracts and averages the dark and light counts
    
    for i in range(len(darks)):
        d = direct + "Original Data/" + date + darks[i] + ".txt"
        Dark_Raw[i] = np.loadtxt(d,skiprows=preamble)[:,1]
        
        
    for i in range(pixels):
        for j in range(len(darks)):
            dark_count[i] += Dark_Raw[j][i]
        dark_count[i] = dark_count[i]/len(darks)

    for i in range(len(lights)):
        l = direct + "Original Data/" + date + lights[i] + ".txt"
        Light_Raw[i] = np.loadtxt(l,skiprows=preamble)[:,1]
        
        
    for i in range(pixels):
        for j in range(len(lights)):
            light_count[i] += Light_Raw[j][i]
        light_count[i] = light_count[i]/len(lights)
    

############################################################################################
    


sets_A = "ADEFGHIJKLMNOPQRSTU"  #Change these to contain the datasets you want to normalise
    
sets_B = "CDEFGHIJKLMNOPQRSTUVWXYZ"
 
sets_C = "AB"

sets_D = "CDEFGHIJKLMNOPQRSTUV"

sets_E = "CDFGHIJKLMNOPQRSTUVWXYZ"

sets_F = "CDEFGKLM"

sets_G = "C"

def ReadWrite(sets,letter):
    
    for i in range(len(sets)):
        
        fname = direct + "Original Data/" + date + letter + sets[i] + ".txt"
        
        Raw_Data = np.loadtxt(fname,skiprows=preamble)[:,1]
        for k in range(pixels):
            if light_count[k] - dark_count[k] <= 0:
                intensity[k] = 0
        
            else:
                intensity[k] = (Raw_Data[k]-dark_count[k])/(light_count[k]-dark_count[k])
                if intensity[k] > 2:
                    intensity[k] = 0
    
        for p in range(6):
            intensity[1070+p] = intensity[1064+p]
        intensity[1439] = intensity[1440]

        
        writename = direct + date + letter + sets[i] + "N3.txt"
        f = open(writename,"w+")
        f.write("""This is here
        to allow the same format
        as the other
        text files
        so i don't have to update
        my code.
        It just needs to be
        14 lines long.
        How about a Haiku?
        It's coming soon
        Next line..
        I enjoy coding
        I hope you enjoy reading
        This little haiku""")
        for j in range(pixels):
            line =  "\n" + str(wavelength[j]) + " " + str(intensity[j])
            f.write(line)
        f.close()
        

Averaging()

#ReadWrite(sets_A,"A")
# ReadWrite(sets_B,"B")
# ReadWrite(sets_C,"C")
# ReadWrite(sets_D,"D")
# ReadWrite(sets_E,"E")
# ReadWrite(sets_F,"F")
ReadWrite(sets_G,"G")
#ReadWrite(sets_Z,"Z")









