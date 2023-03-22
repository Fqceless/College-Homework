#include "OPT.h"

OPT::OPT(int nPF, std::vector<int> rSS) : ReplacementAlgorithm(nPF) {
    this->refrenceStringStream = rSS;
    pageFaultCount = 0;
    this->counter = 0;
}

void OPT::insert(int pageNumber)
{
    // Implement FIFO page replacement algorithm
    bool didPageFault = true;
    // copy of the frameList for use in finding the optimal page fault
    std::list<int> copyList = frameList;

    //Check if it's already in the list
    for (auto i : frameList) {
        if (pageNumber == i) {
            didPageFault = false;
            break;
        }
    }

    /*Optimal Page-fault Finder*/
    //Compares each next input (fortune-telling)
    //With each page in the frameList
    //If it finds a match, it kicks it out of the copy
    //Last one(s) standing are optimal to kick out of the real list
    int pageToRemove;
    if (didPageFault) {
        if (frameList.size() >= pageFrameCount) {
            for (int j = counter; j < refrenceStringStream.size(); j++) {
                //uses frameList to iterate instead of copyList
                //because we are removing from copyList
                for (auto i : frameList) {
                    if (!(copyList.size() == 1)) {
                        if (refrenceStringStream.at(j) == i) {
                            copyList.remove(i);
                        }
                    }
                    else { break; }
                }
                if (copyList.size() == 1) { break; }
            }
            //remove last one standing from real list
            //in the case of more than one left in the copy, they all work.
            frameList.remove(copyList.front());
        }
        frameList.push_front(pageNumber);
        // Increment pageFaultCount if a page fault occurs
        pageFaultCount++;
    }
    //keeps track of where we are in the refrence string
    counter++;
}

