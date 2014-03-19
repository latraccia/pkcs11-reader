/*
 * PKCS#11-Reader, a utility to read PKCS#11 card information from any card and card reader.
 * Copyright (C) 2014 La Traccia http://www.latraccia.it/en/
 * Developed by Francesco Pontillo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see [http://www.gnu.org/licenses/].
 */

package it.latraccia.pkcs11.reader.test;

/**
 * Date: 18/03/14
 * Time: 16.59
 *
 * @author Francesco Pontillo
 */
public class Const {
    public static final String ENCRYPTION_PASSWORD = "0123456789abcdef";

    public static class Actalis {
        public static final String DECRYPTED = "SERIAL_NUMBER=65235973081121999\n" +
                "VALIDITY_FROM=2013-09-25T13:32:50.000+02:00\n" +
                "VALIDITY_TO=2018-09-25T13:32:50.000+02:00\n" +
                "ISSUER_CN=Actalis CA per certificati CNS G1\n" +
                "ISSUER_OU=CNS Certification Service Provider\n" +
                "ISSUER_O=Actalis S.p.A./03358520967\n" +
                "ISSUER_C=IT\n" +
                "SUBJECT_CN=\"NTZRCC81H16L418X/2444024000002384.cifJCU3a1W7XVru2h79d2sR3gjE=\"\n" +
                "SUBJECT_OU=Ulss 8 Asolo\n" +
                "SUBJECT_O=Ulss 8 Asolo\n" +
                "SUBJECT_C=IT\n\n";

        public static final String ENCRYPTED = "r7sFAjPWcivgKjSHO2iU6I2W8Ze6OUvGM05Dry8Ew9VEJq+M4gr3vcbFD3NYAnz" +
                "YOOUcoVPLDeeovXrY5EpqthBYRKKTttm4QadlEQyzjidHXDttBzoSptDDGaf/wi+F0v2LCxxw1Su2nAn3HojcfgKNu4BjMnPB5Zl5Qt" +
                "ruMC9Z12qe7J5ByM9r+q8g0R/FJNO+C8DAPXi6pMVP3WSK9J2QNtSpwgOwFqvTtRUI9XEYIDGSGLfbaWdE56zLKU3biJsZlLRw5sVyh" +
                "eDvwCJvNP11unpppVCvaWrjVxa5J8HkQmfFHjcoaGg4P1nVRgtr9w05SlMHWsWDsAfvScqUrjUJrbMcgzuviaUyWitMjG7oI3adcTK4" +
                "ajAFF7aah9Vf+ZnDV7CufW1e9MRinJ+wWlsMjuSi7ljTH7MeugubaFU0XbcsroFdD5bRDtVRT0hUpi/A138hf3AIDdxx2zwoemR7ymb" +
                "Kq8sgEv9BHyJuZEFJXCUlhVdkozrKOzakqKCBdDGSX7krwtspDWcP6HDBbQ==";
    }

    public static class InfoCert {
        public static final String DECRYPTED = "SERIAL_NUMBER=3094481\n" +
                "VALIDITY_FROM=2013-01-30T12:35:32.000+01:00\n" +
                "VALIDITY_TO=2016-01-30T01:00:00.000+01:00\n" +
                "ISSUER_CN=InfoCert Firma Qualificata\n" +
                "ISSUER_OU=Certificatore Accreditato\n" +
                "ISSUER_SERIALNUMBER=07945211006\n" +
                "ISSUER_O=INFOCERT SPA\n" +
                "ISSUER_C=IT\n" +
                "SUBJECT_CN=Rocco Antezza\n" +
                "SUBJECT_DNQ=20101498441\n" +
                "SUBJECT_SERIALNUMBER=IT:NTZRCC81H16L418X\n" +
                "SUBJECT_GIVENNAME=ROCCO\n" +
                "SUBJECT_SURNAME=ANTEZZA\n" +
                "SUBJECT_O=U.L.SS. 8 ASOLO/00896810264\n" +
                "SUBJECT_C=IT\n\n";

        public static final String ENCRYPTED = "Q8WI/90Rzo54Z7vXxIBz6sBW+sqbf+owWOv7Cz2L0lm9XRV9k0BAMcCIHKQCLcBfkgSWBSlMr" +
                "86wSyQ4wvWm4ASNCnQFllz3yEP3bAGnn11YdG1f1i6JYcjuk1RauVqim3c8kBt6jeCdofb5IKTjA6b4kOVXLlUBmRjIsETgF1W3QpO1i" +
                "yIV6YXf7pBIb0iYp7RB3zfMmGo681m6b6WKExj6ZmYJxghEfga8EgMfjFDjLOo/3ZCcsELZ3rPugOkdLWR8At6DlFLsGIL682f/pEq3K" +
                "DMgodUOjus3qgKv7o4KU7bVtddHAeZT+wq5d5SOAF4NGwWLh861qAtqz3sYhk5k7icVSDL1OeirqgQvojwuvKGhmjY8KW166JsBCpxc9" +
                "BeBxOqqjBi5IsDFXo4+OmSs8PTrNpEVMwdOE5UPcpUe54U3dcafDxNiU+zdEduRb7roZSJX7Kv1ffXGxDZbrshKI7jMjfvCuzw4nozSt" +
                "y2aairyJJ3hQIBDuI4PaoYzz53KD7ngTuA6rla0ZhhoMho6V+tyd2en8ZW06TZDUtZQ5XCirTeCmWN4tgwSAprfrvu27HL2l8vFc8x2d" +
                "o0TaQ==";
    }
}
