# System-Management-Library

#### 1. publications commands:

type + command + params

<div align="center">
  
| Command |           Param            | Optional |
|:-------:|:--------------------------:| :----:|
|   add   |  title author year status  | - |
| getAll  |             -              | sort_type |
| search  |          kewword           | sort_type |
| remove  |             id             | - |
| update  | id title author year status | -|

</div>


#### 2. users commands:

user + command + params

<div align="center">

| Command |       Params       | Optional |
|:-----:|:------------------:|:------:|
| register | username  password | - |
| login | username password  | - |
| logout |         -          | - |

</div>

#### 3. borrow commands:

borrow + command + params

<div align="center">

| Command |       Params       | Optional |
|:-----:|:------------------:|:------:|
| borrow/return | publicationId | - |
| history | - | - |

</div>