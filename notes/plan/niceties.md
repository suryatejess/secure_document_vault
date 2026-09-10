1. make this app working for any one across the globe. 
    - so take time zone under consideration
    - considering the client time would not be a good option

    i. finding from the BE 
    - considering the geo location would be a nice thing. i believe this could be done on the BE based on the XFF('X-Forwarded-FOR') header 
    - theres a possibitliy someone could manually populating the XFF header 

    ii. send the time in standard UTC in epoch
