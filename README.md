# the why 
- building this project as part of learning spring security 

- this project would not be built in one go. there'll be iterations on how this is going to be built. 


# iterations : 

  ## iteration 1 - Documents MVP : 
    - Upload / list-mine / download / delete
    - Ownership = uploader. Every query scoped to the current user.

  ## iteration 2 - Sharing & Authorization : 
    - Grant another user read/write on a document
    - A permission model (document_access table)
    - Method security (@PreAuthorize / a custom PermissionEvaluator)

  ## iteration 3 - Infra improvements and deployments : 
    - host the project 
    - upload documents to s3 / gcs / some cloud storage 

  ## iteration 4 - Spaces : 
    - group documents
    - roles within a space (owner/viewer) ( i am intentionally not including editor. because this is just supposed to be a secure vault and not a collaboration space. plans might change later. for now, i do not wish to support editing )

  ## iteration 5 - Versioning : 
    - immutable versions of a file. 
    - owner gets to decide what version of that file to be active 

  ## iteration 6 - Hardening :
    - audit log 
    - rate limiting

  ## iteration 7 - Hardening_2 : 
    - expiring share links 

