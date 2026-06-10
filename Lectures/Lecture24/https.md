HTTPS

სერვერისთვის სერტიფიკატის დამატება

```shell 
keytool -genkeypair -alias mycert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650
```

## მარტივი მაგალითები

### RSA

1. p = 3, q = 11
2. n = p × q = 33
3. φ(n) = (p-1)(q-1) = 20
4. e = 3 (public exponent)
5. e × d ≡ 1 (mod 20) => d = 7

public key = (e=3, n=33)

private key = (d=7, n=33)

-----

### Digital signature შემოწმება

1. ბრაუზერი სთხოვს სერვერს სერტიფიკატს
2. სერვერი აგზავნის სერტიფიკატს + public key = (e=3, n=33) (RSA)
3. სერვერი აგზავნის მესიჯს 'Hello' hash = 4
4. და აგზავნის signature = hash^d mod n = 4^7 mod 33 = 16
5. ბრაუზერს აქვს public key = (e=3, n=33) და ითვლის hash = signature^e mod n = 4
6. fromSignature = 4 = calculatedHash = 4 ✅ ანუ sender-ს ნამდვილად ჰქონია private key
7. ვთქვათ ჰაკერმა შეცვალა მესიჯი HELXO, hash გახდება 9
8. signature ისევ ძველია და ბრაუზერი ნახავს, რომ (fromSignature = 4) != (calculatedHash = 9) ❌

------

### Diffie-Hellman

1. შეთანხმდნენ საჯარო g = 5, p = 23
2. ბრაუზერმა აირჩია საიდუმლო a = 6
3. გამოთვალა A = 5^6 mod 23 = 8
4. გააგზავნა სერვერთან A = 8
5. სერვერმა აირჩია საიდუმლო b = 15
6. გამოთვალა B = 5^15 mod 23 = 19
7. გააგზავნა ბრაუზერთან B = 19
8. ბრაუზერმა გამოთვალა B^a mod p = 19^6 mod 23 = 2
9. სერვერმა გამოთვალა A^b mod p = 8^15 mod 23 = 2
10. ორივემ მიიღო shared secret = 2

პაროლის გაგზავნისას ხდება დაშიფრვა ამ secret-ის გამოყენებით.

ჰაკერს შეუძლია ნახოს: g = 5, p = 23, A = 8, B = 19,
მაგრამ არ იცის secret = 2, a = 6, b = 15 და ამ ინფორმაციის გარეშე ვერ გამოთვლის. (Discrete Logarithm Problem)
