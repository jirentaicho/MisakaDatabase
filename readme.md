# MisakaDatabase

Substitutes for a simple database.

### Installation

Still in the process of evolving, but the jar files are in the build/libs folder

### Usage

It is very simple to use.
Specify the entity class type and the primary key type.
Just pass the entity class information in the constructor.

```java
// create your repository
MisakaRepository<Integer, User> misakaRepository = new MdbRepository<>(User.class);
MisakaRepository<String, Lot> lotRepository = new MdbRepository<>(Lot.class);

// use repository
misakaRepository.insert(1, new User(1,"admin"));
lotRepository.insert("MDB20001", new Lot("MDB20001"));

User user = misakaRepository.findById(1).get();
Lot lot = lotRepository.findById("MDB20001").get();

assertEquals(1, user.getId());
assertEquals("MDB20001", lot.getLotNo());
```

### Will

MisakaDatabase will continue to improve and provide better MisakaDatabase.