package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class MemoryPool
 @brief Stores unused memory objects (see Memory) so they can be reused when needed.

 When the reference counter of a Memory object reaches 0 then the Memory object may not be deleted immediately; infact,
  if the memory managed by the memory pool matches some predefined requisites, then the Memory object is temporarily
  stored in the memory pool and reused when a request for a similar Memory object is received.

 When a memory object is not used for a while then it is deleted permanently.




 @fn void MemoryPool::flush()
 @brief Discard all the currently unused memory.

 */