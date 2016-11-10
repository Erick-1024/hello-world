package test.cfca.sadk.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;

import junit.framework.TestCase;
import test.cfca.sadk.testdata.SM2;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.org.bouncycastle.asn1.ASN1Set;
import cfca.sadk.org.bouncycastle.asn1.pkcs.CertificationRequest;
import cfca.sadk.org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Signature;
import cfca.sadk.org.bouncycastle.asn1.x500.X500Name;
import cfca.sadk.org.bouncycastle.util.io.pem.PemObject;
import cfca.sadk.org.bouncycastle.util.io.pem.PemReader;
import cfca.sadk.util.Base64;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.P10Request;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class P10DecodedTestCase extends TestCase {

    Session session = null;

    static final String base64P10CFCA = "MIH5MIGeAgEAMD4xGDAWBgNVBAMMD2NlcnRSZXF1aXNpdGlvbjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMQswCQYDVQQGEwJDTjBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABGBUJJZl5KF+TeDgHyyRwlHWXsBVA2LNbo9o+Vtjo9MISia8lgJBLsv8Tz4PrjhIQ1eDgQB9xQhGSKtpxTglxcUwDAYIKoEcz1UBg3UFAANIADBFAiEA+yShjpr1XRlR9GynpqNG8bXxZK3jM7rrGCDn3CygchkCIBUc5V4MNifgZxd9qsfY99on9MeX7d24Fsce/ws82iSZ";
    static final String base64P10ICBC = "MIIB3jCCAYECAQAwIzEMMAoGA1UECgwDYmpzMRMwEQYDVQQDDApJQ0JDX1NNMl8wMIIBUzCCAQsGByqGSM49AgEwgf8CAQEwLAYHKoZIzj0BAQIhAP////7/////////////////////AAAAAP//////////MEQEIP////7/////////////////////AAAAAP/////////8BCAo6fqenZ9eNE1ankvPZQmn85eJ9RWrj5LdvL1BTZQOkwRBBDLEriwfGYEZX5kERmo5yZSP4wu/8mYL4XFaRYkzTHTHvDc2ovT2d5xZvc7ja2khU9Cph3zGKkdAAt8y5SE58KACIQD////+////////////////cgPfayHGBStTu/QJOdVBIwIgMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMcDQgAET/ho2dgMG+RaPi2jzl80bTbengWiVlSK9rMS/Ukjb4c4ComzbjN0yJPD1k1V0MIpMbEVgM2cN0N5mwy2djmS6qAAMAwGCCqBHM9VAYN1BQADSQAwRgIhAL4BZXDCK3pdUswZ4oddNhMpnqOONipnn5HV8C49sfXfAiEAnGLhQmaC+vM5wz480O2++NYwMvcryOhL9o5ioXs4jXk=";
    static final String base64P10ICBC_2 = "MIHcMIGAAgAwHzEdMBsGA1UEAwwUSUNCQ19CSl9QMDAyMDAwMDAwMDUwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQiYgDj7uvUWbt3XPfuWom/UkNfsEdh2IWeC95O/VLAZNu4rK8cTRAGtSJbgUUiZHpxwbEeSKDcKVsQ6S1DZCK9oAAwDAYIKoEcz1UBg3UFAANJADBGAiEA6j7OZQ94b6bNMLspSz4+HppfCd5yEdpMScenOS5tZcUCIQDDgDeGohO1B2ytqZUHrRfs+aw3M0aWwoOAynsG/EnvAA==";
    static final String base64P10ICBC_3 = "MIHbMIGAAgAwHzEdMBsGA1UEAwwUSUNCQ19CSl9QMDAyMDAwMDAwMDMwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAATh+B4OZuoZrVXke63RbWt5KRf43L95iuhXvton33r8+6OQ9J/66CucFx7FgVJGuEXYWDMsCD8hHA8ZxBtBOsQXoAAwDAYIKoEcz1UBg3UFAANIADBFAiEAwQ0nRM42tUDGGWz4X+ZH5RksOZ+tCoTiTQ9NT9w5MuwCIFJeGD596HlykkENO+/tQU8HpvH3AxZXQCVpMq8Ss22Y";
    final String[] p10TextsTable = new String[] {//
    // RSA2048-SHA256
            "MIIDxjCCAq4CAQAwPjEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExCzAJBgNVBAYTAkNOMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAio21ZBYbLdAQtgQXHtsQv/mIHk2I5+kjjfJqU7S+1ST+EOcK8neN3CU2fIipvOcFQC0GT1F8LBnyKyUgk7staGKvMXghrw8BCDxDACdO/U4YDzHiUnadWDdQJ+kBFhXhrnEksvRqZjCDfjC5hmYTkRdCqIVsy1OgF61lT26bmZxPoOWi6e6pRb653LMEnUHNo5WvvEQBFO7JhYkZ5KIv548EeRBl2WmidwOMi2rrQD3PujSwzOdBzx3mJKcdpNOXPyv8OSoVA2BKS2CyORABWSZsoP530yiYV7YUWH9KG2yg4zIuLb1UbtiRsNgR0quZVPFiQcviuE1+lAc1Ejvz6wIDAQABoIIBQTATBgkqhkiG9w0BCQcTBjExMTExMTCCASgGCSqGSIb3DQEJPwSCARkwggEVAgEBBIIBDjCCAQoCggEBAIn156dqhbfLhwXyghlM2j5dK8PuUtYL946hKuiRg6wiVWIdHtImKOyIUUxJDTKQJ+OqHUjU4mOcQ58ruPPj8eRBPWaE5xF33gE3ymK3MdmqtDuoM6Tg5fzKqrdBIsIGOLE646UXRH5b9hZZjL97XuqAe3jKmGTCaV6ESh1sE3e3aUFrqYS+uEXddGXhZnZ+mI6bChSr7Np7Nn4wt9hBtp1yNw/xmoXRBwsu03/QBpnltPi1sPKxevDbH9Uo+dVqJfL8VMYcJFqdEf3DXeNH83/VM4STfbC23P4AdO5U/MOu82dToqrmMOy6mYRkA94Wg5Xt0rNdoXRJB7XMrDl2wGECAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAT0a9amWXXSykSBmKDQnsTDP1fBvAvwj0Sz3lKVSQBxMxm44/VbqFpuxskfBSOpsaGYv4HDN0noZRPkEEbMNyhGilx11BrGVKXDbOP0CDNBIlWwkvyMm0IL7uUHDzns7Omf/rjiJd1zh91vHTmaXX9py7QrCW0xGoT7USurJxxnFFe8KOAgnXXNfImq1b9WX6wmBUlHv0R86L0VYnRT3+1QaBJVhckudqT+CEwyXRGPDrDM0lptSu+QUST7LpD+Huu0u00DswZBBQWp2JwhgSJxlqpOKAs8ak2A8L7KHetdlOrR+cKOjQ8XBHMLJ20cy0iCY/AgsKu6nhVHbmdbPKgw==",
            // RSA1024-SHA256
            "MIICOjCCAaMCAQAwPjEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExCzAJBgNVBAYTAkNOMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCk8i82L17VUuOfBnB5SJcAhyLlm/oriopOoXALIXQP/iql6U4y6/AGRAWwykl3H5jP3sgjs4gG3DPZGe8owKbPJNk311vLxZXYXz+VzfTsLUTiDUC6Df1jSeS381r+mFMahU2MgUDs/NFITxQO+GOY4x/Yorogheb/J/4ElAlLQIDAQABoIG7MBMGCSqGSIb3DQEJBxMGMTExMTExMIGjBgkqhkiG9w0BCT8EgZUwgZICAQEEgYwwgYkCgYEAs0kFAFOSyJTG/3UeCqc0g6Hh8QqC3MReqR9KMsrH4AeFV1MLOjrlYnQS/QT8EFVyk7lZOkujxl6KRdCuz0cewJmwRqdczu5nyGVRKnzt+QQ1vyhMaXJdHPwD4Su5nNO9ARiwKeTNQZvB3xnlsoFwFDqmqxB/ccDKaqBB58qadL0CAwEAATANBgkqhkiG9w0BAQsFAAOBgQA4Um1P8lqq30QW5K3d4gbLpJcfqZe4h9FFzmmUNxm9MqhDMSHdNVL5Iuq0YeEP+wDjfkt+weHlYO5Stad1zjuVzXH1bop6VgRcTYr4vsQbOw8atH3+rS6ZF+PBbTr+axsDfonRgSs1E7jeHoTTuFqYMnuluUf5xbYMaxdFn+f0OA==",
            // RSA1024-SHA512
            "MIICOjCCAaMCAQAwPjEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExCzAJBgNVBAYTAkNOMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCH90uJkprPO7OcwZajPTMq6FL66ALS+Ffk4TlsK0+4kX1nxlZn5PlXPXQVLz+/yhXlSHtHzLlOQT/W6j7ihVPPcd1dSBtBS8b+ZU5BJFV61HZKqgCu0jZntWL0CN+OnuRDQRF0l+zRnPY5YHb7orXMtblL/SjsGQVJV6JxGg+nPwIDAQABoIG7MBMGCSqGSIb3DQEJBxMGMTExMTExMIGjBgkqhkiG9w0BCT8EgZUwgZICAQEEgYwwgYkCgYEAhJih7hMseQDoWaZl42ybph2MqfQD7Ako3hzg1FJ42Tek8zX3lc38elk7WZcZVZQagji8zKnExtuBEtk9dk2yPdbBqink79k759tRv5VNowMD6C/ACCr5tuN58Cbm1PeUdaXHg1pLRjaUyAmsBehezcg+Hxt44InSKh//PRYXOJECAwEAATANBgkqhkiG9w0BAQ0FAAOBgQBovpOIxvkQWBa93stulO5E9rOQOX9bJq73jgYhXCpSi/eqZCHuHITLXyDKAGWWRTFCkS/235OCih1WThZtG37UP7MxrvCUnMZfuN2dWvqU5BgdmS1ghPA7cDeFbPPTDdiiEvB4E1Gcmv1Ogh6PWwwZZrlXgqp5ZsJ7GeEUDJXESA==",
            // RSA1024-SHA1
            "MIICOjCCAaMCAQAwPjEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExCzAJBgNVBAYTAkNOMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2YBfsBnIci0TcuPu4tnElqvx22GnjS354JulWLHqICtZkyf4TaLG8wu5VDlW2aC6u3Ek341wZr706EqvjA32np0p/fkNckwE4Vk6OOjpg7F2LdVcaQcc1P3tPuSk0ac69sNyvyb8A4dN1acX0L45TLyt0ey80/Ossx609SUpVlwIDAQABoIG7MBMGCSqGSIb3DQEJBxMGMTExMTExMIGjBgkqhkiG9w0BCT8EgZUwgZICAQEEgYwwgYkCgYEAvFkbChFBmmgirz4TqVpfvmy/TV6oiFUijJ0lvR0tuKtPZkK1BkKE/ZnO6vSA6r/MLGEypUetqVW38wxy8luEDQ7TZzxqb6467T8UGDutYPINs/6Jg/3Idjan3rAdxc9oXlOsR8pAPyVbEMwXNLbgIl9DRYoS/OPDCmb1J8oF4fMCAwEAATANBgkqhkiG9w0BAQUFAAOBgQBaMtUsOReXlOj80kzRgNlmUYVnOjbyokTwm3FFoYc198/hXldy+nTn2SJKq95bgzMj5YRZh6dkAA4aAI0GTjXzxa7m0JRFyExXyMEsk6kzqKfwxloUrVXv8za4dCsY+sXbC0JfhQQdKvArKVsK1On66xfkDhBipwusHqYmyOqCPQ==",
            // RSA1024-MD5
            "MIICOjCCAaMCAQAwPjEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExCzAJBgNVBAYTAkNOMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEWxBxRPv33lxJkit5iTjn9fYlsuWn/o+0mLHkmEoH/y+wK6MIDUpvj4aAgGrRL20JzvnMMthuJHv5m8aKwMd7ZfSVCuVwz/dkl2QhQI4VVfIAY7xV45bgogrA8f8yeYK0os2BaG27n8giFcbKbW4sN5pPSOfSHECsMm6r9diNOQIDAQABoIG7MBMGCSqGSIb3DQEJBxMGMTExMTExMIGjBgkqhkiG9w0BCT8EgZUwgZICAQEEgYwwgYkCgYEA1Q2VS61VLFn5p61VDl0PsmW1DpfWNdd/kFMlsry2CimfwkdDrvLej76Fwyhhk0Auf+hNfMXdbstEwvq3jUJVsmQICFOKqZULSrJGioqGETJ2l/YbaCkVJQyKpFFgiFX7rgysT3hVtZ3pGYWYCXCVyRB71i2oIqPkr1R4R7++POUCAwEAATANBgkqhkiG9w0BAQQFAAOBgQAafg5L/P4CvuULmxgGFFeh1oT5DRqMide8wIu+FMB9FYfOI8tKZNydDGXH8ChK42VgNPwHFiWFXtn7supGWsa+Q3HNtmDOuzsdA1R64pRndB/SVDgAz2r86Vs5DXvH2X/CxBEde5eTAFY69s+3TQbzE3C3p9Q/4UF5QKpppVs4hg==",
            // SM2-SM3
            "MIIBtTCCAVgCAQAwPjEYMBYGA1UEAwwPY2VydFJlcXVpc2l0aW9uMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExCzAJBgNVBAYTAkNOMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAElDnYb/P65akV3Zc4LU6v082KNqMGSAKzlpQGYA8B7269YajZ54v7GBHOvcVW1Yd/7Os9bF9+skDkBc0QvefnKKCBtzATBgkqhkiG9w0BCQcTBjExMTExMTCBnwYJKoZIhvcNAQk/BIGRMIGOAgEBBIGIALQAAAABAADQG9PqQ+hksA0qWhP/0Si0uo1SJTgAMFv3DQhcXn4ZHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAc4slZZvn37W5IaVisfWCV7FCrKzQe1oKihmWD/mgy2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADAMBggqgRzPVQGDdQUAA0kAMEYCIQCciupi/t3zrhmqF2TbRjwxs26WsjJsKotGeaW7D2xeSgIhAKngegG5rFVOZI76vpZHMdBP0//XmXJ228Y01aAs0Ecq" };

    final String[] p10PublickeysTable = new String[] {//

            "30820122300D06092A864886F70D01010105000382010F003082010A02820101008A8DB564161B2DD010B604171EDB10BFF9881E4D88E7E9238DF26A53B4BED524FE10E70AF2778DDC25367C88A9BCE705402D064F517C2C19F22B252093BB2D6862AF317821AF0F01083C4300274EFD4E180F31E252769D58375027E9011615E1AE7124B2F46A6630837E30B9866613911742A8856CCB53A017AD654F6E9B999C4FA0E5A2E9EEA945BEB9DCB3049D41CDA395AFBC440114EEC9858919E4A22FE78F04791065D969A277038C8B6AEB403DCFBA34B0CCE741CF1DE624A71DA4D3973F2BFC392A1503604A4B60B239100159266CA0FE77D3289857B614587F4A1B6CA0E3322E2DBD546ED891B0D811D2AB9954F16241CBE2B84D7E940735123BF3EB0203010001",//
            "30819F300D06092A864886F70D010101050003818D00308189028181008293C8BCD8BD7B554B8E7C19C1E5225C021C8B966FE8AE2A293A85C02C85D03FF8AA97A538CBAFC0191016C32925DC7E633F7B208ECE201B70CF6467BCA3029B3C9364DF5D6F2F1657617CFE5737D3B0B513883502E837F58D2792DFCD6BFA614C6A1536320503B3F345213C503BE18E638C7F628AE882179BFC9FF81250252D0203010001",//
            "30819F300D06092A864886F70D010101050003818D003081890281810087F74B89929ACF3BB39CC196A33D332AE852FAE802D2F857E4E1396C2B4FB8917D67C65667E4F9573D74152F3FBFCA15E5487B47CCB94E413FD6EA3EE28553CF71DD5D481B414BC6FE654E4124557AD4764AAA00AED23667B562F408DF8E9EE44341117497ECD19CF6396076FBA2B5CCB5B94BFD28EC19054957A2711A0FA73F0203010001",//
            "30819F300D06092A864886F70D010101050003818D0030818902818100B66017EC06721C8B44DCB8FBB8B67125AAFC76D869E34B7E7826E9562C7A880AD664C9FE1368B1BCC2EE550E55B6682EAEDC4937E35C19AFBD3A12ABE3037DA7A74A7F7E435C930138564E8E3A3A60EC5D8B75571A41C7353F7B4FB9293469CEBDB0DCAFC9BF00E1D37569C5F42F8E532F2B747B2F34FCEB2CC7AD3D494A55970203010001",//
            "30819F300D06092A864886F70D010101050003818D0030818902818100845B107144FBF7DE5C49922B798938E7F5F625B2E5A7FE8FB498B1E4984A07FF2FB02BA3080D4A6F8F8680806AD12F6D09CEF9CC32D86E247BF99BC68AC0C77B65F4950AE570CFF764976421408E1555F20063BC55E396E0A20AC0F1FF327982B4A2CD81686DBB9FC82215C6CA6D6E2C379A4F48E7D21C40AC326EABF5D88D390203010001",//
            "3059301306072A8648CE3D020106082A811CCF5501822D034200049439D86FF3FAE5A915DD97382D4EAFD3CD8A36A3064802B3969406600F01EF6EBD61A8D9E78BFB1811CEBDC556D5877FECEB3D6C5F7EB240E405CD10BDE7E728",//
    };

    final String[] p10TempPublickeysTable = new String[] {//
            "30820122300D06092A864886F70D01010105000382010F003082010A028201010089F5E7A76A85B7CB8705F282194CDA3E5D2BC3EE52D60BF78EA12AE89183AC2255621D1ED22628EC88514C490D329027E3AA1D48D4E2639C439F2BB8F3E3F1E4413D6684E71177DE0137CA62B731D9AAB43BA833A4E0E5FCCAAAB74122C20638B13AE3A517447E5BF616598CBF7B5EEA807B78CA9864C2695E844A1D6C1377B769416BA984BEB845DD7465E166767E988E9B0A14ABECDA7B367E30B7D841B69D72370FF19A85D1070B2ED37FD00699E5B4F8B5B0F2B17AF0DB1FD528F9D56A25F2FC54C61C245A9D11FDC35DE347F37FD53384937DB0B6DCFE0074EE54FCC3AEF36753A2AAE630ECBA99846403DE168395EDD2B35DA1744907B5CCAC3976C0610203010001",//
            "30819F300D06092A864886F70D010101050003818D0030818902818100B34905005392C894C6FF751E0AA73483A1E1F10A82DCC45EA91F4A32CAC7E0078557530B3A3AE5627412FD04FC10557293B9593A4BA3C65E8A45D0AECF471EC099B046A75CCEEE67C865512A7CEDF90435BF284C69725D1CFC03E12BB99CD3BD0118B029E4CD419BC1DF19E5B28170143AA6AB107F71C0CA6AA041E7CA9A74BD0203010001",//
            "30819F300D06092A864886F70D010101050003818D00308189028181008498A1EE132C7900E859A665E36C9BA61D8CA9F403EC0928DE1CE0D45278D937A4F335F795CDFC7A593B59971955941A8238BCCCA9C4C6DB8112D93D764DB23DD6C1AA29E4EFD93BE7DB51BF954DA30303E82FC0082AF9B6E379F026E6D4F79475A5C7835A4B463694C809AC05E85ECDC83E1F1B78E089D22A1FFF3D161738910203010001",//
            "30819F300D06092A864886F70D010101050003818D0030818902818100BC591B0A11419A6822AF3E13A95A5FBE6CBF4D5EA88855228C9D25BD1D2DB8AB4F6642B5064284FD99CEEAF480EABFCC2C6132A547ADA955B7F30C72F25B840D0ED3673C6A6FAE3AED3F14183BAD60F20DB3FE8983FDC87636A7DEB01DC5CF685E53AC47CA403F255B10CC1734B6E0225F43458A12FCE3C30A66F527CA05E1F30203010001",//
            "30819F300D06092A864886F70D010101050003818D0030818902818100D50D954BAD552C59F9A7AD550E5D0FB265B50E97D635D77F905325B2BCB60A299FC24743AEF2DE8FBE85C3286193402E7FE84D7CC5DD6ECB44C2FAB78D4255B2640808538AA9950B4AB2468A8A8611327697F61B682915250C8AA451608855FBAE0CAC4F7855B59DE9198598097095C9107BD62DA822A3E4AF547847BFBE3CE50203010001",//
            "3059301306072A8648CE3D020106082A811CCF5501822D03420004D01BD3EA43E864B00D2A5A13FFD128B4BA8D52253800305BF70D085C5E7E191C738B25659BE7DFB5B921A562B1F58257B142ACACD07B5A0A8A19960FF9A0CB60",//
    };

    final String[] p10AttributesTable = new String[] {//
            "31820141301306092A864886F70D01090713063131313131313082012806092A864886F70D01093F04820119308201150201010482010E3082010A028201010089F5E7A76A85B7CB8705F282194CDA3E5D2BC3EE52D60BF78EA12AE89183AC2255621D1ED22628EC88514C490D329027E3AA1D48D4E2639C439F2BB8F3E3F1E4413D6684E71177DE0137CA62B731D9AAB43BA833A4E0E5FCCAAAB74122C20638B13AE3A517447E5BF616598CBF7B5EEA807B78CA9864C2695E844A1D6C1377B769416BA984BEB845DD7465E166767E988E9B0A14ABECDA7B367E30B7D841B69D72370FF19A85D1070B2ED37FD00699E5B4F8B5B0F2B17AF0DB1FD528F9D56A25F2FC54C61C245A9D11FDC35DE347F37FD53384937DB0B6DCFE0074EE54FCC3AEF36753A2AAE630ECBA99846403DE168395EDD2B35DA1744907B5CCAC3976C0610203010001",//
            "3181BB301306092A864886F70D01090713063131313131313081A306092A864886F70D01093F04819530819202010104818C30818902818100B34905005392C894C6FF751E0AA73483A1E1F10A82DCC45EA91F4A32CAC7E0078557530B3A3AE5627412FD04FC10557293B9593A4BA3C65E8A45D0AECF471EC099B046A75CCEEE67C865512A7CEDF90435BF284C69725D1CFC03E12BB99CD3BD0118B029E4CD419BC1DF19E5B28170143AA6AB107F71C0CA6AA041E7CA9A74BD0203010001",//
            "3181BB301306092A864886F70D01090713063131313131313081A306092A864886F70D01093F04819530819202010104818C308189028181008498A1EE132C7900E859A665E36C9BA61D8CA9F403EC0928DE1CE0D45278D937A4F335F795CDFC7A593B59971955941A8238BCCCA9C4C6DB8112D93D764DB23DD6C1AA29E4EFD93BE7DB51BF954DA30303E82FC0082AF9B6E379F026E6D4F79475A5C7835A4B463694C809AC05E85ECDC83E1F1B78E089D22A1FFF3D161738910203010001",//
            "3181BB301306092A864886F70D01090713063131313131313081A306092A864886F70D01093F04819530819202010104818C30818902818100BC591B0A11419A6822AF3E13A95A5FBE6CBF4D5EA88855228C9D25BD1D2DB8AB4F6642B5064284FD99CEEAF480EABFCC2C6132A547ADA955B7F30C72F25B840D0ED3673C6A6FAE3AED3F14183BAD60F20DB3FE8983FDC87636A7DEB01DC5CF685E53AC47CA403F255B10CC1734B6E0225F43458A12FCE3C30A66F527CA05E1F30203010001",//
            "3181BB301306092A864886F70D01090713063131313131313081A306092A864886F70D01093F04819530819202010104818C30818902818100D50D954BAD552C59F9A7AD550E5D0FB265B50E97D635D77F905325B2BCB60A299FC24743AEF2DE8FBE85C3286193402E7FE84D7CC5DD6ECB44C2FAB78D4255B2640808538AA9950B4AB2468A8A8611327697F61B682915250C8AA451608855FBAE0CAC4F7855B59DE9198598097095C9107BD62DA822A3E4AF547847BFBE3CE50203010001",//
            "3181B7301306092A864886F70D010907130631313131313130819F06092A864886F70D01093F04819130818E02010104818800B4000000010000D01BD3EA43E864B00D2A5A13FFD128B4BA8D52253800305BF70D085C5E7E191C0000000000000000000000000000000000000000000000000000000000000000738B25659BE7DFB5B921A562B1F58257B142ACACD07B5A0A8A19960FF9A0CB600000000000000000000000000000000000000000000000000000000000000000",//

    };

    final String[] p10SignalgTable = new String[] {//
    "sha256WithRSAEncryption",//
            "sha256WithRSAEncryption",//
            "sha512WithRSAEncryption",//
            "sha1WithRSAEncryption",//
            "md5WithRSAEncryption",//
            "sm3WithSM2Encryption"//
    };

    final int[] p10SignlenTable = new int[] { 256, 128, 128, 128, 128, 64 };
    final int[] p10keysizeTable = new int[] { 2048, 1024, 1024, 1024, 1024, 256 };

    final boolean[] p10AlgorithmTable = new boolean[] { false, false, false, false, false, true };

    final boolean generatedRSA2048Flag = false;

    protected void setUp() throws Exception {
        session = TestReady.openSession();

    }

    protected void tearDown() throws Exception {
        session = null;
    }

    public void testLoadP10FromHailong() throws Exception {

        P10Request p10Request = new P10Request(session);

        PemObject pem = loadPemResource("TestData/p10/P10hailong/single-hard-sm2-8.csr");

        p10Request.load(pem.getContent());
        assertTrue("testLoadP10FromHailong", true);
        assertTrue("testLoadP10FromHailong", p10Request.getPublicKey() != null);
        assertTrue("testLoadP10FromHailong", p10Request.getSignature() != null);
        System.err.println(p10Request.getPublicKey());
        System.err.println();

        p10Request = new P10Request(session);
        pem = loadPemResource("TestData/p10/P10hailong/single-soft-rsa2048.csr");
        p10Request.load(pem.getContent());
        assertTrue("testLoadP10FromHailong", true);
        assertTrue("testLoadP10FromHailong", p10Request.getPublicKey() != null);
        assertTrue("testLoadP10FromHailong", p10Request.getSignature() != null);
        System.err.println(p10Request.getPublicKey());
        System.err.println();

        p10Request = new P10Request(session);
        pem = loadPemResource("TestData/p10/P10hailong/single-soft-sm2.csr");
        p10Request.load(pem.getContent());
        assertTrue("testLoadP10FromHailong", true);
        assertTrue("testLoadP10FromHailong", p10Request.getPublicKey() != null);
        assertTrue("testLoadP10FromHailong", p10Request.getSignature() != null);
        System.err.println(p10Request.getPublicKey());
        System.err.println();

        p10Request = new P10Request(session);
        pem = loadPemResource("TestData/p10/P10hailong/double-soft-sm2.csr");
        p10Request.load(pem.getContent());
        assertTrue("testLoadP10FromHailong", true);
        assertTrue("testLoadP10FromHailong", p10Request.getPublicKey() != null);
        assertTrue("testLoadP10FromHailong", p10Request.getSignature() != null);
        assertTrue("testLoadP10FromHailong", p10Request.getTemporaryPublicKey() != null);
        System.err.println(p10Request.getPublicKey());
        System.err.println(p10Request.getTemporaryPublicKey());
        System.err.println();

        p10Request = new P10Request(session);
        pem = loadPemResource("TestData/p10/P10hailong/double-hard-sm2-6.csr");
        p10Request.load(pem.getContent());
        assertTrue("testLoadP10FromHailong", true);
        assertTrue("testLoadP10FromHailong", p10Request.getPublicKey() != null);
        assertTrue("testLoadP10FromHailong", p10Request.getSignature() != null);
        assertTrue("testLoadP10FromHailong", p10Request.getTemporaryPublicKey() != null);
        System.err.println(p10Request.getPublicKey());
        System.err.println(p10Request.getTemporaryPublicKey());
        System.err.println();

    }

    static PemObject loadPemResource(String resource) throws IOException {

        PemReader p = new PemReader(new InputStreamReader(new FileInputStream(resource)));
        PemObject o = p.readPemObject();
        p.close();
        return o;
    }

    public void testLoadByteArray() throws PKIException {

        P10Request p10Request = new P10Request(session);
        byte[] p10RequestData = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            p10Request = new P10Request(session);
            p10RequestData = Base64.decode(p10TextsTable[i]);
            p10Request.load(p10RequestData);
            assertTrue("testLoadByteArray[" + i + "]", p10Request.getSignatureAlgorithm().equals(p10SignalgTable[i]));
            assertTrue("testLoadByteArray[" + i + "]", p10Request.getSignature().length == p10SignlenTable[i]);
            assertTrue("testLoadByteArray[" + i + "]", p10Request.getKeySize() == p10keysizeTable[i]);
        }
        assertTrue("testLoadByteArray", true);

        p10RequestData = Base64.decode(base64P10CFCA);

        p10Request = new P10Request(session);
        p10Request.load(p10RequestData);
        assertTrue("testLoadByteArray", p10Request.getSignature().length == 64);

        p10RequestData = Base64.decode(base64P10ICBC);

        p10Request = new P10Request(session);
        p10Request.load(p10RequestData);
        assertTrue("testLoadByteArray", p10Request.getSignature().length == 64);

        p10RequestData = Base64.decode(base64P10ICBC_2);

        p10Request = new P10Request(session);
        p10Request.load(p10RequestData);
        assertTrue("testLoadByteArray", p10Request.getSignature().length == 64);

        p10RequestData = Base64.decode(base64P10ICBC_3);

        p10Request = new P10Request(session);
        p10Request.load(p10RequestData);
        assertTrue("testLoadByteArray", p10Request.getSignature().length == 64);

    }

    public void testGetSubject() throws PKIException {
        callGetSubjectTest("testGetSubject");
    }

    public void testGetSubjectFromP10Request() throws PKIException {
        callGetSubjectTest("testGetSubjectFromP10Request");
    }

    final void callGetSubjectTest(String testName) throws PKIException {

        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;
        byte[] p10ReqBytes = null;

        String subject = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            p10ReqBytes = Base64.decode(p10TextsTable[i]);
            certificationRequest = CertificationRequest.getInstance(p10ReqBytes);
            p10Request.load(certificationRequest);

            subject = p10Request.getSubjectFromP10Request(p10TextsTable[i].getBytes());
            assertTrue(testName + "[" + i + "]", subject != null);
            assertTrue(testName + "[" + i + "]", subject.equals(p10Request.getSubject()));

        }
        assertTrue(testName, true);

        p10Request = new P10Request(session);
        subject = p10Request.getSubjectFromP10Request(base64P10CFCA.getBytes());
        assertTrue(testName, subject != null);

        p10Request = new P10Request(session);
        subject = p10Request.getSubjectFromP10Request(base64P10ICBC.getBytes());
        assertTrue(testName, subject != null);
    }

    public void testGetSignatureAlgorithmFromP10Request() throws PKIException {
        callGetSignatureAlgorithm("testGetSignatureAlgorithmFromP10Request");
    }

    public void testGetSignatureAlgorithm() throws PKIException {
        callGetSignatureAlgorithm("testGetSignatureAlgorithm");
    }

    final void callGetSignatureAlgorithm(String testName) throws PKIException {
        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;
        byte[] p10ReqBytes = null;

        String signatureAlgorithm = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            p10ReqBytes = Base64.decode(p10TextsTable[i]);
            certificationRequest = CertificationRequest.getInstance(p10ReqBytes);
            p10Request.load(certificationRequest);

            signatureAlgorithm = p10Request.getSignatureAlgorithmFromP10Request(p10TextsTable[i].getBytes());
            assertTrue(testName + "[" + i + "]", signatureAlgorithm != null);
            assertTrue(testName + "[" + i + "]", signatureAlgorithm.equals(p10SignalgTable[i]));
            assertTrue(testName + "[" + i + "]", signatureAlgorithm.equals(p10Request.getSignatureAlgorithm()));

        }
        assertTrue(testName, true);

        p10Request = new P10Request(session);
        signatureAlgorithm = p10Request.getSignatureAlgorithmFromP10Request(base64P10CFCA.getBytes());
        assertTrue(testName, signatureAlgorithm != null);

        p10Request = new P10Request(session);
        signatureAlgorithm = p10Request.getSignatureAlgorithmFromP10Request(base64P10ICBC.getBytes());
        assertTrue(testName, signatureAlgorithm != null);
    }

    public void testGetSignatureFromP10Request() throws PKIException {
        callGetSignature("testGetSignatureFromP10Request");
    }

    public void testGetSignature() throws PKIException {
        callGetSignature("testGetSignature");
    }

    final void callGetSignature(String testName) throws PKIException {
        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;
        byte[] p10ReqBytes = null;

        byte[] signature = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            p10ReqBytes = Base64.decode(p10TextsTable[i]);
            certificationRequest = CertificationRequest.getInstance(p10ReqBytes);
            p10Request.load(certificationRequest);

            signature = p10Request.getSignatureFromP10Request(p10TextsTable[i].getBytes());
            assertTrue(testName + "[" + i + "]", signature != null);

            if (p10AlgorithmTable[i]) {
                assertTrue(testName + "[" + i + "]", new ASN1SM2Signature(signature).equals(new ASN1SM2Signature(p10Request.getSignature())));
            } else {
                assertTrue(testName + "[" + i + "]", Arrays.equals(signature, p10Request.getSignature()));
            }

        }
        assertTrue(testName, true);
    }

    public void testIsP10RequestSignatureValid() throws PKIException {
        callP10RequestSignatureValid("testIsP10RequestSignatureValid");
    }

    public void testGetP10RequestVerifyState() throws PKIException {
        callP10RequestSignatureValid("testGetP10RequestVerifyState");
    }

    final void callP10RequestSignatureValid(String testName) throws PKIException {
        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;
        byte[] p10ReqBytes = null;

        boolean valid;
        for (int i = 0; i < p10TextsTable.length; i++) {
            p10ReqBytes = Base64.decode(p10TextsTable[i]);
            certificationRequest = CertificationRequest.getInstance(p10ReqBytes);
            p10Request.load(certificationRequest);

            valid = p10Request.isP10RequestSignatureValid(p10TextsTable[i].getBytes());
            assertTrue(testName + "[" + i + "]", valid);
            assertTrue(testName + "[" + i + "]", p10Request.getP10RequestVerifyState());

        }
        assertTrue(testName, true);

        p10Request = new P10Request(session);
        valid = p10Request.isP10RequestSignatureValid(base64P10CFCA.getBytes());
        assertTrue(testName, valid);

        p10Request = new P10Request(session);
        valid = p10Request.isP10RequestSignatureValid(base64P10ICBC.getBytes());
        assertTrue(testName, valid);
    }

    public static void main(String[] args) throws Exception {
        Session session = TestReady.openSession();
        P10Request p10Request = new P10Request(session);
        boolean valid = p10Request.isP10RequestSignatureValid(base64P10CFCA.getBytes());
        System.err.println(valid);

        session = TestReady.openSession();
        p10Request = new P10Request(session);
        valid = p10Request.isP10RequestSignatureValid(base64P10ICBC.getBytes());
        System.err.println(valid);

    }

    public void testGetAttributes() throws PKIException, IOException {
        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            certificationRequest = CertificationRequest.getInstance(Base64.decode(p10TextsTable[i]));
            p10Request.load(certificationRequest);
            assertTrue("testGetAttributes[" + i + "]", p10Request.getAttributes() != null);
            assertTrue("testGetAttributes[" + i + "]", Arrays.equals(p10Request.getAttributes().getEncoded(), HexBin.decode(p10AttributesTable[i])));

        }
        assertTrue("testGetAttributes", true);
    }

    public void testGetPubKeyFromSubPubKeyInfo() throws PKIException, IOException {
        if (generatedRSA2048Flag) {
            callGetPubKeyFromSubPubKeyInfo(new Mechanism(Mechanism.SHA256_RSA), 2048);
        }
        callGetPubKeyFromSubPubKeyInfo(new Mechanism(Mechanism.SHA256_RSA), 1024);
        callGetPubKeyFromSubPubKeyInfo(new Mechanism(Mechanism.SHA512_RSA), 1024);
        callGetPubKeyFromSubPubKeyInfo(new Mechanism(Mechanism.SHA1_RSA), 1024);

        callGetPubKeyFromSubPubKeyInfo(new Mechanism(Mechanism.SM3_SM2), 256);
    }

    final void callGetPubKeyFromSubPubKeyInfo(final Mechanism mechanism, int kLength) throws PKIException, IOException {
        P10Request p10Request = new P10Request(session);

        X500Name holder = new X500Name("CN=TEST,C=CN");
        ASN1Set attributes = ASN1Set.getInstance(HexBin.decode(p10AttributesTable[0]));

        final boolean smFalg = mechanism.getMechanismType().contains("SM2");
        KeyPair keyPair = null;
        if (smFalg) {
            keyPair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.SM2), kLength, session);
        } else {
            keyPair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.RSA), kLength, session);
        }
        PublicKey publicKey = keyPair.getPublic();

        CertificationRequestInfo certificationRequestInfo = p10Request.generateCertificationRequestInfo(holder, attributes, publicKey);

        PublicKey key = p10Request.getPubKeyFromSubPubKeyInfo(certificationRequestInfo.getSubjectPublicKeyInfo());

        assertTrue("callGetPubKeyFromSubPubKeyInfo[" + mechanism + "]", key != null);
        assertTrue("callGetPubKeyFromSubPubKeyInfo[" + mechanism + "]", key.equals(publicKey));
    }

    public void testGetPublicKey() throws PKIException {
        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            certificationRequest = CertificationRequest.getInstance(Base64.decode(p10TextsTable[i]));
            p10Request.load(certificationRequest);
            assertTrue("testGetPublicKey[" + i + "]", p10Request.getPublicKey() != null);
            assertTrue("testGetPublicKey[" + i + "]", Arrays.equals(p10Request.getPublicKey().getEncoded(), HexBin.decode(p10PublickeysTable[i])));

        }
        assertTrue("testGetPublicKey", true);
    }

    public void testGetTemporaryPublicKey() throws PKIException {
        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            certificationRequest = CertificationRequest.getInstance(Base64.decode(p10TextsTable[i]));
            p10Request.load(certificationRequest);
            assertTrue("testGetTemporaryPublicKey[" + i + "]", p10Request.getTemporaryPublicKey() != null);
            assertTrue("testGetTemporaryPublicKey[" + i + "]",
                    Arrays.equals(p10Request.getTemporaryPublicKey().getEncoded(), HexBin.decode(p10TempPublickeysTable[i])));

        }
        assertTrue("testGetTemporaryPublicKey", true);
    }

    public void testGetTemporaryPublicKeyDataFromAttributes() throws PKIException {
        callGetTemporaryPublicKey("testGetTemporaryPublicKeyDataFromAttributes");
    }

    public void testGetTemporaryPublicKeyFromAttributes() throws PKIException {
        callGetTemporaryPublicKey("testGetTemporaryPublicKeyFromAttributes");
    }

    final void callGetTemporaryPublicKey(String testName) throws PKIException {
        P10Request p10Request = new P10Request(session);
        CertificationRequest certificationRequest = null;

        String base64TemporaryPublicKeyText = null;

        byte[] base64TemporaryPublicKeyData = null;
        byte[] encodeTemporaryPublicKeyData = null;
        for (int i = 0; i < p10TextsTable.length; i++) {
            certificationRequest = CertificationRequest.getInstance(Base64.decode(p10TextsTable[i]));
            p10Request.load(certificationRequest);

            base64TemporaryPublicKeyData = p10Request.getTemporaryPublicKeyDataFromAttributes(p10Request.getAttributes());
            encodeTemporaryPublicKeyData = Base64.decode(base64TemporaryPublicKeyData);

            base64TemporaryPublicKeyText = p10Request.getTemporaryPublicKeyFromAttributes(p10Request.getAttributes());

            assertTrue(testName + "[" + i + "]", base64TemporaryPublicKeyText.contains(new String(base64TemporaryPublicKeyData)));

            if ("RSA".equals(p10Request.getPublicKey().getAlgorithm())) {
                assertTrue(testName + "[" + i + "]", p10TempPublickeysTable[i].contains(HexBin.encode(encodeTemporaryPublicKeyData)));
            } else {
                String textTemporaryPublicKey = "00B4000000010000D01BD3EA43E864B00D2A5A13FFD128B4BA8D52253800305BF70D085C5E7E191C0000000000000000000000000000000000000000000000000000000000000000738B25659BE7DFB5B921A562B1F58257B142ACACD07B5A0A8A19960FF9A0CB600000000000000000000000000000000000000000000000000000000000000000";
                assertTrue(testName + "[" + i + "]", textTemporaryPublicKey.equals(HexBin.encode(encodeTemporaryPublicKeyData)));
            }

        }
        assertTrue(testName, true);
    }

    private void sm2CheckedSignedASNBytes(byte[] p10Bytes, Mechanism mechanism) {
        assertTrue("sm2CheckedSignedASNBytes[" + mechanism + "]", SM2.sm2CheckedSignedBytesFormatForASN1(p10Bytes, mechanism));
    }

}
