package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class Memory
 @brief This class holds an allocated array of bytes.

 New instances of this class should be obtained through the class MemoryPool: call MemoryPool::getMemory() in order to
  obtain a new instance of memory.



 @fn Memory::Memory()
 @brief Construct an empty Memory object. Use resize() to allocate the memory.


 @fn Memory::Memory(int requestedSize)
 @brief Constructs the memory object and allocate the requested amount of memory.

 @param initialSize the initial size of the allocated memory, in bytes



 @fn void Memory::transfer(Memory transferFrom)
 @brief Transfer the content from another memory object.

 The source memory object will transfer the ownership of the managed memory to this object and then will reference an
  empty memory area (size = 0).

 @param transferFrom the object from which the memory must be transferred



 @fn void Memory::copyFrom(Memory copyFrom)
 @brief Copy the content of the memory managed by another memory object into the memory managed by this object.

 @param sourceMemory a pointer to the memory object from which the data has to be copied



 @fn void Memory::clear()
 @brief Clear the content of the memory object and set its size to 0 bytes.



 @fn void Memory::resize(int newSize)
 @brief Resize the memory buffer.

 @param newSize  the new size of the buffer, in bytes




 @fn void Memory::reserve(int reserveSize)
 @brief Reserve the specified quantity of bytes for the memory object. This doesn't modify the actual size of the
        memory object.

 @param reserveSize   the number of bytes to reserve for the memory object.



 @fn int Memory::size()
 @brief Return the size of the managed memory in bytes.

 @return the size of the managed memory, in bytes




 @fn long Memory::data(byte[] buffer)
 @brief Copies the bytes from the managed memory to the supplied array.

 If the size of the bytes buffer is less than the memory's size, then the function just returns the required size,
  otherwise copies the content of the memory into the bytes buffer and then returns the number of bytes copied.

 @return the required size of the array, in bytes.




 @fn void Memory::assign(byte[] buffer)
 @brief Copy the specified array of bytes into the managed memory.

 @param buffer the bytes buffer containing the bytes to be copied



 @fn boolean Memory::empty()
 @brief Return true if the size of the managed memory is 0.

 @return true if the managed memory's size is 0 or false otherwise

 */