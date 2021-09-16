########################################################################################
#_____Importing libraries and declaring things that won't change from time to time_____#
########################################################################################

import matplotlib.pyplot as plt
from matplotlib.pyplot import figure
import numpy as np
import math as m

preamble = 14                 
pixels = 2048

intensity = [0 for i in range(pixels)]
energy = [0 for i in range(pixels)]


##############################################################################################
#_____Declaring items that will change from time to time, but not within one set of data_____#  
##############################################################################################           

date = "210211"
direct = "/Users/solread/Masters Project/Intensity Plotter/Python Project/" + "data_" + date + "/"

orig_fname = direct + "Original Data/" + date + "AA.txt"   #Allows for quicker acquisition of wavelength

wavelength = np.loadtxt(orig_fname,skiprows=preamble)[:,0]  #Loads wavelength into array

for i in range(pixels):     #Turns wavelength to energy scale in eV)
    energy[i] = 1242.375/wavelength[i]


##############################################################################
#____________Declaring functions for use in the main body of code____________#
##############################################################################

def Plotting(descrip, colour):

    
    plt.plot(energy[x_low:x_high],intensity[x_low:x_high], label=descrip, color=colour)
    axes = plt.gca()
    axes.set_ylim([-0.05,logmax])
    axes.set_xlabel('Energy (eV)',fontname='Computer Modern',fontsize=14)
    axes.set_ylabel('Apparent Absorption log(1/R)',fontname='Computer Modern',fontsize=14)
    
    plt.grid(True)
    plt.legend(loc="upper left")
    plt.show
    plt.savefig('PW1M.png',dpi=600)

    
#########################################################################################

def Plotting2(descrip,colour):

    for i in range(x_low,x_high):
        intensity[i] = -m.log10(intensity[i])
    plt.plot(energy[x_low:x_high],intensity[x_low:x_high], label=descrip, color = colour)
    axes = plt.gca()
    axes.set_ylim([-0.05,logmax])   
    axes.set_xlabel('Energy (eV)',fontname='Computer Modern',fontsize=14)
    axes.set_ylabel('Apparent absorption log(1/R)',fontname='Computer Modern',fontsize=14)
    
    plt.grid(True)
    plt.legend(loc="upper left")
    plt.show
    #plt.savefig('RB1.png',dpi=600)

    
##############################################################################
#_____________________Declaring the plotting constants_______________________#
##############################################################################

x_low = 350  #Lowest index of energy
x_high = 1800   #Highest index of energy
ymax = 31000  #Highest value to plot y to for indiv. plots
logmax = 1.55   #Highest y for log plots
                  # ----------------------------------------------------------------------------------- |
sets = ["HH","AN","GO","HD"]  #Sets to be plotted                                                       
label = ["Pink","1:1 ratio","Model","White"]     #Labels corresponding to sets
col = ["fuchsia","blue","red","grey"]          

##############################################################################                          |
#__________________________Reading in the files______________________________#                          |
##############################################################################                          |

def ReadFile(choice):                                                                                   #
                                                                                                        #
    fname = direct + date + choice + "N6.txt"                                                            #
    Raw = np.loadtxt(fname,skiprows=preamble)[:,1]                                                      #
    for i in range(pixels):
        intensity[i] = Raw[i]
                                                                                                        #
                                                                                                        #
    # norm = max(intensity[150:1000])     #Change as necessary to select where peak value is -----------#
    # for i in range(pixels):
    #     intensity[i] = intensity[i]/norm
    
    # small = min(x for x in intensity if x != 0)
    # for i in range(pixels):
    #     if intensity[i] <= 0:
    #         intensity[i] += abs(small)


############################################################################################

def Comparison():
    
    for i in range(len(sets)):
        choice = sets[i]
        descrip = label[i]
        colour = col[i]
        ReadFile(choice)
        Plotting(descrip, colour)
        
        
############################################################################################

def Logarithm2():
    
    for i in range(len(sets)):
        choice = sets[i]
        descrip = label[i]
        colour = col[i]
        ReadFile(choice)
        Plotting2(descrip, colour)
        
        
############################################################################################

menu = input("""Please input the option you would like:
             1. Plot a single dataset using linear R intensity
             2. Plot the datasets using linear R intensity
             3. Plot the datasets using (log(1/R)) intensity
             Enter here: """)
             
if menu == "1":
    choice = input("Please input the dataset (e.g AA) that you'd like to plot: ")
    ReadFile(choice)
    Plotting("Aluminium Foil","blue")
    
elif menu == "2":
    Comparison()
    
elif menu == "3":
    Logarithm2()

else:
    print("Sorry! Try Again")



