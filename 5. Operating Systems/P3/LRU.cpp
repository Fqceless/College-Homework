#include "LRU.h"

LRU::LRU(int numPageFrames) : ReplacementAlgorithm(numPageFrames) {
    pageFaultCount = 0;
}

void LRU::insert(int pageNumber)
{
    // Implement LRU page replacement algorithm
    
    bool didPageFault = true;
    //Check if it's already in the list
    //if it is, put it at the front
    for (auto i : frameList) {
        if (pageNumber == i) {
            frameList.remove(pageNumber);
            frameList.push_front(pageNumber);
            didPageFault = false;
            break;
        }
    }

    //Check if we need to page fault
    if (didPageFault) {
        //if the list is full, push out the least recently used element
        if (frameList.size() >= pageFrameCount) {
            frameList.pop_back();
        }
        frameList.push_front(pageNumber);
        // Increment pageFaultCount if a page fault occurs
        pageFaultCount++;
    }
}

