import java.time.Instant;

// entities

enum UserType {
  ADMIN,
  USER
}

class User {
  long id; // pk
  String name;
  String password; // hashed
  UserType userType;
}

class Auction {
  long id; // pk
  String name;
  String description;
  double startingPrice;
  Instant startTime;
  Instant endTime;

  public boolean isActive() {
    return endTime == null;
  }
}

class Bid {
  long userId; // foreign key
  long auctionId; // fk
  long amount;
}

POST /user   {name, password}
GET  /user?name={name}?password={password}

POST   /auction {name, description, startingPrice}
PUT    /auction {name, desctiptions, startingPrice}
DELETE /auction {id}
GET    /auction {name/description/startTime/isActive} List<Auction>

POST  /bid/{userId}/{auctionId}/{amount} // Bid
GET   /bid/{auctionId} // List<Bid> for auction
GET   /bid/highest/{auctionId} // return Username + price

bid-ის სერვისის კლასი უნდა იყოს synchronized და უნდა შევამოწმოთ რომ ბიდი არის ყველაზეე მაღალი
