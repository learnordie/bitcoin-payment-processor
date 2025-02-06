;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.impl.bip39-test
  (:require  [clojure.test :as t]
             [com.github.learnordie.wallet.impl.bip39 :as sut])
  (:import [org.apache.commons.codec.binary Hex]))

(defn- hex->bytes
  [str]
  (Hex/decodeHex (String/.toCharArray str)))

(t/deftest entropy->mnemonic
  (t/is (= "abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon about"
           (sut/entropy->mnemonic (hex->bytes "00000000000000000000000000000000"))))
  (t/is (= "legal winner thank year wave sausage worth useful legal winner thank yellow"
           (sut/entropy->mnemonic (hex->bytes "7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f"))))
  (t/is (= "letter advice cage absurd amount doctor acoustic avoid letter advice cage above"
           (sut/entropy->mnemonic (hex->bytes "80808080808080808080808080808080"))))
  (t/is (= "zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo wrong"
           (sut/entropy->mnemonic (hex->bytes "ffffffffffffffffffffffffffffffff"))))
  (t/is (= "abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon agent"
           (sut/entropy->mnemonic (hex->bytes "000000000000000000000000000000000000000000000000"))))
  (t/is (= "legal winner thank year wave sausage worth useful legal winner thank year wave sausage worth useful legal will"
           (sut/entropy->mnemonic (hex->bytes "7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f"))))
  (t/is (= "letter advice cage absurd amount doctor acoustic avoid letter advice cage absurd amount doctor acoustic avoid letter always"
           (sut/entropy->mnemonic (hex->bytes "808080808080808080808080808080808080808080808080"))))
  (t/is (= "zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo when"
           (sut/entropy->mnemonic (hex->bytes "ffffffffffffffffffffffffffffffffffffffffffffffff"))))
  (t/is (= "abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon art"
           (sut/entropy->mnemonic (hex->bytes "0000000000000000000000000000000000000000000000000000000000000000"))))
  (t/is (= "legal winner thank year wave sausage worth useful legal winner thank year wave sausage worth useful legal winner thank year wave sausage worth title"
           (sut/entropy->mnemonic (hex->bytes "7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f"))))
  (t/is (= "letter advice cage absurd amount doctor acoustic avoid letter advice cage absurd amount doctor acoustic avoid letter advice cage absurd amount doctor acoustic bless"
           (sut/entropy->mnemonic (hex->bytes "8080808080808080808080808080808080808080808080808080808080808080"))))
  (t/is (= "zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo vote"
           (sut/entropy->mnemonic (hex->bytes "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))))
  (t/is (= "ozone drill grab fiber curtain grace pudding thank cruise elder eight picnic"
           (sut/entropy->mnemonic (hex->bytes "9e885d952ad362caeb4efe34a8e91bd2"))))
  (t/is (= "gravity machine north sort system female filter attitude volume fold club stay feature office ecology stable narrow fog"
           (sut/entropy->mnemonic (hex->bytes "6610b25967cdcca9d59875f5cb50b0ea75433311869e930b"))))
  (t/is (= "hamster diagram private dutch cause delay private meat slide toddler razor book happy fancy gospel tennis maple dilemma loan word shrug inflict delay length"
           (sut/entropy->mnemonic (hex->bytes "68a79eaca2324873eacc50cb9c6eca8cc68ea5d936f98787c60c7ebc74e6ce7c"))))
  (t/is (= "scheme spot photo card baby mountain device kick cradle pact join borrow"
           (sut/entropy->mnemonic (hex->bytes "c0ba5a8e914111210f2bd131f3d5e08d"))))
  (t/is (= "horn tenant knee talent sponsor spell gate clip pulse soap slush warm silver nephew swap uncle crack brave"
           (sut/entropy->mnemonic (hex->bytes "6d9be1ee6ebd27a258115aad99b7317b9c8d28b6d76431c3"))))
  (t/is (= "panda eyebrow bullet gorilla call smoke muffin taste mesh discover soft ostrich alcohol speed nation flash devote level hobby quick inner drive ghost inside"
           (sut/entropy->mnemonic (hex->bytes "9f6a2878b2520799a44ef18bc7df394e7061a224d2c33cd015b157d746869863"))))
  (t/is (= "cat swing flag economy stadium alone churn speed unique patch report train"
           (sut/entropy->mnemonic (hex->bytes "23db8160a31d3e0dca3688ed941adbf3"))))
  (t/is (= "light rule cinnamon wrap drastic word pride squirrel upgrade then income fatal apart sustain crack supply proud access"
           (sut/entropy->mnemonic (hex->bytes "8197a4a47f0425faeaa69deebc05ca29c0a5b5cc76ceacc0"))))
  (t/is (= "all hour make first leader extend hole alien behind guard gospel lava path output census museum junior mass reopen famous sing advance salt reform"
           (sut/entropy->mnemonic (hex->bytes "066dca1a2bb7e8a1db2832148ce9933eea0f3ac9548d793112d9a95c9407efad"))))
  (t/is (= "vessel ladder alter error federal sibling chat ability sun glass valve picture"
           (sut/entropy->mnemonic (hex->bytes "f30f8c1da665478f49b001d94c5fc452"))))
  (t/is (= "scissors invite lock maple supreme raw rapid void congress muscle digital elegant little brisk hair mango congress clump"
           (sut/entropy->mnemonic (hex->bytes "c10ec20dc3cd9f652c7fac2f1230f7a3c828389a14392f05"))))
  (t/is (= "void come effort suffer camp survey warrior heavy shoot primary clutch crush open amazing screen patrol group space point ten exist slush involve unfold"
           (sut/entropy->mnemonic (hex->bytes "f585c11aec520db57dd353c69554b21a89b20fb0650966fa0a9d6f74fd989d8f")))))

(t/deftest valid-mnemonic?
  (t/testing "Valid mnemonics"
    (t/is (sut/valid-mnemonic? "abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon about"))
    (t/is (sut/valid-mnemonic? "legal winner thank year wave sausage worth useful legal winner thank yellow"))
    (t/is (sut/valid-mnemonic? "zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo wrong"))
    ;; case insensitive
    (t/is (sut/valid-mnemonic? "ZOO zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo wrong")))
  (t/testing "Invalid mnemonics"
    (t/is (false? (sut/valid-mnemonic? "tht/is t/is not a valid mnemonic")))
    (t/is (false? (sut/valid-mnemonic? "abandon abandon abandon")))
    (t/is (false? (sut/valid-mnemonic? "legal winner thank year wave sausage worth useful legal winner thank thank")))
    (t/is (false? (sut/valid-mnemonic? "zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo"))))
  (t/testing "Edge cases"
    (t/is (false? (sut/valid-mnemonic? "")))
    (t/is (false? (sut/valid-mnemonic? " ")))
    (t/is (thrown? java.lang.AssertionError (sut/valid-mnemonic? nil)))
    (t/is (thrown? java.lang.AssertionError (sut/valid-mnemonic? ["abandon" "abandon" "abandon" "abandon" "abandon" "abandon" "abandon" "abandon" "abandon" "abandon" "abandon" "about"])))))
