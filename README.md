# FlexionIntegration
Resolving flexion coding challenge

Testing improvement:
using Junit test.
This project work with java 8. So I used Junit4.
Better to use Junit5 with java 11+.

I found a bug:
In buy api the itemId can be anything, including null. I think the buy api create new item with the passed itemId.
So the buy api never run failed if the api send some string.


Other remark:
The test cases only model the correct operation, but in reality the handling of possible errors must also be tested.

Consume purchase description wrongly
api url:
http://sandbox.flexionmobile.com/javachallenge/rest/developer/{developerId}/consume/{purchaseId}
purchaseId String An ID of an existing Purchase
but in teh interface api request the Purchase object:
void consume(Purchase var1);
