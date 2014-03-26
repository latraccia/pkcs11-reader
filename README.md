# PKCS#11 Reader
*A utility to read PKCS#11 card information from any card and card reader.*

## How to build

The project is based on `pom.xml` files, so you need to have Maven installed and configured. Clone the repo and call:

```shell
    $ maven clean install
```

This will proceed on the main `pom.xml` to clean and build the project.

## How to use

To read information from a card, connect the card reader, insert the card, then call the `ReadCLI` class on the built `jar` with the proper parameters:

```shell
	$ java -jar pkcs11-reader-0.1-SNAPSHOT-jar-with-dependencies.jar ReadCLI
```

The card information will be printed on the standard output, so you can redirect it to any supported output stream.

### Parameters

The following list contains all of the accepted parameters.

* **REQUIRED** `--driver` or `-d`, the library
path for the SSCD (e.g. smart card), must be a valid path
* `--password` or `-p`, the PIN of the card to read information from
* `--encryption` or `-e`, a 16-characters password to encrypt the found information with (see [Encryption](#encryption)).
* `--log` or `-log`, if present, output will be redirected to the specified file.

### Encryption

The read information will be encrypted, if the `--encryption` parameter is specified, with the following settings:

* `AES` Algorithm
* `CBC` Cipher Mode
* `PKCS5Padding` Padding
* the input 16-byte encryption password

### Example

```shell
    $ java -jar pkcs11-reader-0.1-SNAPSHOT-jar-with-dependencies.jar ReadCLI
		--driver="~/drivers/bit4ipki.dll"
		--password="mypin"
		--encryption="0123456789abcdef"
		--log="~/readcli.log"
```

## License

PKCS#11-Reader is released under the LGPL.

    PKCS#11-Reader, a utility to read PKCS#11 card information from any card and card reader.
    Copyright (C) 2014 La Traccia http://www.latraccia.it/en/
    Developed by Francesco Pontillo

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see [http://www.gnu.org/licenses/].

### Authors

The original **SD-DSS** project has been commissioned by the European Commission (DG MARKT, Directorate E, Services),
financed under the ISA Work Programme (DG DIGIT) in the framework of the implementation of Services Directive.

**PKCS#11-Reader** has been developed by [Francesco Pontillo](mailto:francescopontillo@gmail.com)
([La Traccia](http://www.latraccia.it/en/)).

### Used libraries

PKCS#11-Reader directly uses the following libraries/modules:

* [**SD-DSS**](https://joinup.ec.europa.eu/software/sd-dss), developed by
[**ARHS Developments S.A.**](http://www.arhs-developments.com) (rue Nicolas Bové 2B, L-1253 Luxembourg), released by
2011 European Commission, Directorate-General Internal Market and Services (DG MARKT), B-1049 Bruxelles/Brussel
under **LGPL v3**.
* [**JCommander**](http://jcommander.org/), developed by [**Cédric Beust**](mailto:cedric@beust.com), released under
**Apache 2.0 license**.

You can find a list of referenced libraries' licenses in the `licenses` directory and the related notices in the
`NOTICE` file in the root of the project.