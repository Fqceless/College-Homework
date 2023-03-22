#include "FIFO.h"

FIFO::FIFO(int numPageFrames) : ReplacementAlgorithm(numPageFrames) {
    pageFaultCount = 0;
}

void FIFO::insert(int pageNumber)
{
    // Implement FIFO page replacement algorithm
    
    bool didPageFault = true;
    //Check if it's already in the list
    for (auto i : frameList) {
        if (pageNumber == i) {
            didPageFault = false;
            break;
        }
    }

    //Check if we need to page fault
    if (didPageFault) {
        //if the list is full, push out the first in
        if (frameList.size() >= pageFrameCount) {
            frameList.pop_back();
        }
        frameList.push_front(pageNumber);
        // Increment pageFaultCount if a page fault occurs
        pageFaultCount++;
    }
    
    
    

}

