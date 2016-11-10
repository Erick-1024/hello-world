package test.cfca.sadk.x509.certificate;

import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import cfca.sadk.org.bouncycastle.asn1.ASN1Encodable;
import cfca.sadk.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.sadk.org.bouncycastle.asn1.DERGeneralizedTime;
import cfca.sadk.org.bouncycastle.asn1.DERIA5String;
import cfca.sadk.org.bouncycastle.asn1.DERPrintableString;
import cfca.sadk.org.bouncycastle.asn1.x500.RDN;
import cfca.sadk.org.bouncycastle.asn1.x500.X500Name;
import cfca.sadk.org.bouncycastle.asn1.x500.X500NameStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.BCStrictStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.BCStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.RFC4519Style;
import cfca.sadk.x509.certificate.CFCAStyle;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * <pre>
 * 参会人：
 *        林总，王总，苏才苏、张庆安、刘培旭、王勐、秘相友
 *        
 * 时间：2015年5月14日 下午
 *  
 *  目前我们的产品输出的证书DN有如下三种风格：
 *     1.       风格一: CN项在前，等号左侧大写，等号两边无空格，以逗号空格为分割
 *      示例：CN=051@民生RSA@12323232323232@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN
 *  
 *     2.       风格二: CN项在后，等号左侧大写，等号两边无空格，以逗号为分割
 *     示例：C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生RSA@12323232323232@1
 *  
 *     3.       风格三: CN项在前，等号左侧大写，等号两边无空格，以逗号为分割
 *     示例：CN=051@民生RSA@12323232323232@1,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN
 *  
 * 各产品的支持情况如下：
 *      1.       C语言SADK（客户端+服务器端）、SVS客户端C语言API产品输出的DN为风格一；
 *      2.       Java SADK可以输出一、二两种风格的DN；
 *      3.       CA、RA、预植产品，输出的DN为风格三；
 *  
 * 会议结论：
 *      1.       各产品考虑到各自的兼容性，都不修改代码。
 *      2.       如果在客户那出现不兼容的情况，首先建议客户的程序自己做字符串替换处理，再次我们的产品提供一个转换的API。
 *      3.      各产品对于输入的DN，如有不同格式，应该都可以处理。各产品对输入的DN，不区分大小写、三种风格互认。
 *      4.       Java SADK修改使用手册，推荐用户只使用风格一（即风格二应该是大家都尽量避免使用的）。
 * </pre>
 * 
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public class CFCAStyleTestCase extends TestCase {

    final String DN1 = "CN = CFCA CS SM2 CA,O = China Financial Certification Authority, C = CN";
    final String DN2 = "CN = 051@民生测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";

    /**
     * 风格一: CN项在前，等号左侧大写，等号两边无空格，以逗号空格为分割
     * "CN=051@民生RSA@12323232323232@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN"
     */
    final String DN_STYLE_1 = "CN=051@民生RSA@12323232323232@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN";
    /**
     * 风格二: CN项在后，等号左侧大写，等号两边无空格，以逗号为分割
     * "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生RSA@12323232323232@1"
     * ;
     */
    final String DN_STYLE_2 = "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生RSA@12323232323232@1";
    /**
     * 风格三: CN项在前，等号左侧大写，等号两边无空格，以逗号为分割
     * "CN=051@民生RSA@12323232323232@1,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN"
     * ;
     */
    final String DN_STYLE_3 = "CN=051@民生RSA@12323232323232@1,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN";

    final String[] RDN_STYLE_1 = new String[] { "3127302506035504030C1E30353140E6B091E7949F5253414031323332333233323332333233324031",//
            "31153013060355040B0C0C496E646976696475616C2D31",//
            "3111300F060355040B0C084C6F63616C205241",//
            "31153013060355040A0C0C434643412054455354204341",//
            "310B300906035504061302434E",//
    };
    final String[] RDN_STYLE_2 = new String[] { "310B300906035504061302434E",//
            "31153013060355040A0C0C434643412054455354204341",//
            "3111300F060355040B0C084C6F63616C205241",//
            "31153013060355040B0C0C496E646976696475616C2D31",//
            "3127302506035504030C1E30353140E6B091E7949F5253414031323332333233323332333233324031",//
    };
    final String[] RDN_STYLE_3 = new String[] { "3127302506035504030C1E30353140E6B091E7949F5253414031323332333233323332333233324031",//
            "31153013060355040B0C0C496E646976696475616C2D31",//
            "3111300F060355040B0C084C6F63616C205241",//
            "31153013060355040A0C0C434643412054455354204341",//
            "310B300906035504061302434E",//
    };

    final X500NameStyle bcStrictStyle = BCStrictStyle.INSTANCE;

    final X500NameStyle bcStyle = BCStyle.INSTANCE;

    final X500NameStyle efc4519Style = RFC4519Style.INSTANCE;
    final String ENC_STYLE_2 = "3077310B300906035504061302434E31153013060355040A0C0C4346434120544553542043413111300F060355040B0C084C6F63616C20524131153013060355040B0C0C496E646976696475616C2D313127302506035504030C1E30353140E6B091E7949F5253414031323332333233323332333233324031";

    static String[] displayNames = { "C",//
            "O",//
            "T",//
            "OU",//
            "CN",//
            "L",//
            "ST",//
            "SERIALNUMBER",//
            "SERIALNUMBER",//
            "STREET",//
            "E",//
            "DC",//
            "E",//
            "UID",//
            "SURNAME",//
            "GIVENNAME",//
            "INITIALS",//
            "GENERATION",//
            "unstructuredAddress",//
            "unstructuredName",//
            "UniqueIdentifier",//
            "DN",//
            "Pseudonym",//
            "PostalAddress",//
            "NameAtBirth",//
            "CountryOfCitizenship",//
            "CountryOfResidence",//
            "Gender",//
            "PlaceOfBirth",//
            "DateOfBirth",//
            "PostalCode",//
            "BusinessCategory",//
            "TelephoneNumber",//
            "Name" };

    static String[] attributeTypes = { //
    "c",//
            "o",//
            "t",//
            "ou", //
            "cn", //
            "l",//
            "st", //
            "sn",//
            "serialnumber", //
            "street", //
            "emailaddress",//
            "dc", //
            "e", //
            "uid",//
            "surname", //
            "givenname", //
            "initials", //
            "generation",//
            "unstructuredaddress", //
            "unstructuredname",//
            "uniqueidentifier", //
            "dn", //
            "pseudonym", //
            "postaladdress", //
            "nameofbirth", //
            "countryofcitizenship", //
            "countryofresidence",//
            "gender",//
            "placeofbirth", //
            "dateofbirth", //
            "postalcode", //
            "businesscategory", //
            "telephonenumber", //
            "name"//
    };
    static ASN1ObjectIdentifier[] attributeTypeOIDs = { //
    new ASN1ObjectIdentifier("2.5.4.6"),//C - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.10"),//O - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.12"),//T - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.11"),//OU - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.3"),//CN - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.7"),//L - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.8"),//ST: state, or province name - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.5"),//SN: device serial number name - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.5"),//serialnumber: device serial number name - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("2.5.4.9"),//STREET:  street - StringType(SIZE(1..64))
            new ASN1ObjectIdentifier("1.2.840.113549.1.9.1"), //E: Email address (RSA PKCS#9 extension) - IA5String.
            new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25"),//DC: others
            new ASN1ObjectIdentifier("1.2.840.113549.1.9.1"), //E: Email address (RSA PKCS#9 extension) - IA5String.
            new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1"),//LDAP User id.
            new ASN1ObjectIdentifier("2.5.4.4"),//SURNAME-Naming attributes of type X520name
            new ASN1ObjectIdentifier("2.5.4.42"),//GIVENNAME-Naming attributes of type X520name
            new ASN1ObjectIdentifier("2.5.4.43"),//INITIALS-Naming attributes of type X520name
            new ASN1ObjectIdentifier("2.5.4.44"),//GENERATION-Naming attributes of type X520name
            new ASN1ObjectIdentifier("1.2.840.113549.1.9.8"),//UnstructuredAddress: more from PKCS#9
            new ASN1ObjectIdentifier("1.2.840.113549.1.9.2"),//UnstructuredName,more from PKCS#9
            new ASN1ObjectIdentifier("2.5.4.45"),//UNIQUE_IDENTIFIER
            new ASN1ObjectIdentifier("2.5.4.46"),//DN_QUALIFIER: dnQualifier - DirectoryString(SIZE(1..64)
            new ASN1ObjectIdentifier("2.5.4.65"),//PSEUDONYM: RFC 3039 Pseudonym - DirectoryString(SIZE(1..64)
            new ASN1ObjectIdentifier("2.5.4.16"),//POSTAL_ADDRESS: RFC 3039 PostalAddress - SEQUENCE SIZE (1..6) OF DirectoryString(SIZE(1..30))
            new ASN1ObjectIdentifier("1.3.36.8.3.14"),//NAME_AT_BIRTH:  ISIS-MTT NameAtBirth - DirectoryString(SIZE(1..64)
            new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4"),// RFC 3039 CountryOfCitizenship - PrintableString (SIZE (2)) -- ISO 3166  codes only
            new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5"),// RFC 3039 CountryOfResidence - PrintableString (SIZE (2)) -- ISO 3166  codes only
            new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3"),// RFC 3039 Gender - PrintableString (SIZE(1)) -- "M", "F", "m" or "f"
            new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2"),// RFC 3039 PlaceOfBirth - DirectoryString(SIZE(1..128)
            new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1"),//RFC 3039 DateOfBirth - GeneralizedTime - YYYYMMDD000000Z
            new ASN1ObjectIdentifier("2.5.4.17"),//postalCode - DirectoryString(SIZE(1..40)
            new ASN1ObjectIdentifier("2.5.4.15"),//businessCategory - DirectoryString(SIZE(1..128)
            new ASN1ObjectIdentifier("2.5.4.20"),// id-at-telephoneNumber
            new ASN1ObjectIdentifier("2.5.4.41"),// id-at-name

    };

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStringToValue() throws IOException {
        final X500NameStyle style = CFCAStyle.INSTANCE;

        ASN1ObjectIdentifier oid = null;
        for (int i = 0; i < attributeTypes.length; i++) {
            oid = attributeTypeOIDs[i];

            if (oid.equals(CFCAStyle.EmailAddress) || oid.equals(CFCAStyle.DC)) {
                String value = "TEST@fca.com.cn";
                ASN1Encodable asn1 = style.stringToValue(oid, value);

                assertTrue("testStringToValue", Arrays.equals(asn1.toASN1Primitive().getEncoded(), new DERIA5String(value).getEncoded()));

            } else if (oid.equals(CFCAStyle.DATE_OF_BIRTH)) {
                String value = "20150526160811Z";
                ASN1Encodable asn1 = style.stringToValue(oid, value);
                assertTrue("testStringToValue", Arrays.equals(asn1.toASN1Primitive().getEncoded(), new DERGeneralizedTime(value).getEncoded()));

            } else if (oid.equals(CFCAStyle.C) || oid.equals(CFCAStyle.SN) || oid.equals(CFCAStyle.DN_QUALIFIER) || oid.equals(CFCAStyle.TELEPHONE_NUMBER)) {
                String value = "TEST+";

                ASN1Encodable asn1 = style.stringToValue(oid, value);
                assertTrue("testStringToValue", Arrays.equals(asn1.toASN1Primitive().getEncoded(), new DERPrintableString(value).getEncoded()));

            }
        }

    }

    public void testAttrNameToOID() throws IOException {
        final X500NameStyle style = CFCAStyle.INSTANCE;

        for (int i = 0; i < attributeTypes.length; i++) {
            if (!attributeTypeOIDs[i].equals(style.attrNameToOID(attributeTypes[i]))) {
                fail("mismatch for " + attributeTypes[i]);
            }
        }

        {

            final byte[] enc = HexBin
                    .decode("305e310b300906035504061302415531283026060355040a0c1f546865204c6567696f6e206f662074686520426f756e637920436173746c653125301006035504070c094d656c626f75726e653011060355040b0c0a4173636f742056616c65");

            X500Name n = new X500Name(style, X500Name.getInstance(enc));
            final String dn = "L=Melbourne+OU=Ascot Vale, O=The Legion of the Bouncy Castle, C=AU";
            assertTrue("testAttrNameToOID", dn.equals(n.toString()));

            //
            n = new X500Name(style, "C=AU, O=The Legion of the Bouncy Castle, L=Melbourne+OU=Ascot Vale");
            assertTrue("testAttrNameToOID", Arrays.equals(n.getEncoded(), enc));
        }

        {
            //
            final byte[] enc = HexBin.decode(ENC_STYLE_2);
            X500Name n = new X500Name(style, X500Name.getInstance(enc));

            assertTrue("testAttrNameToOID", DN_STYLE_1.equals(n.toString()));
            n = new X500Name(style, DN_STYLE_2);
            assertTrue("testAttrNameToOID", Arrays.equals(n.getEncoded(), enc));
        }

    }

    public void testAreEqual() {
        final X500NameStyle style = CFCAStyle.INSTANCE;

        boolean areEqual = style.areEqual(new X500Name(style, DN_STYLE_1), new X500Name(style, DN_STYLE_3));

        assertTrue("testAreEqual", areEqual);

        areEqual = style.areEqual(new X500Name(style, DN_STYLE_1), new X500Name(style, DN_STYLE_2));

        assertTrue("testAreEqual", areEqual);
    }

    public void testFromString() throws IOException {
        final X500NameStyle style = CFCAStyle.INSTANCE;
        RDN[] rdn = style.fromString(DN_STYLE_1);
        for (int i = 0; i < rdn.length; i++) {
            assertTrue("testFromString", Arrays.equals(rdn[i].getEncoded(), HexBin.decode(RDN_STYLE_1[i])));
            assertTrue("testFromString", Arrays.equals(rdn[i].getEncoded(), HexBin.decode(RDN_STYLE_3[i])));
        }

        rdn = style.fromString(DN_STYLE_2);
        for (int i = 0; i < rdn.length; i++) {
            assertTrue("testFromString", Arrays.equals(rdn[i].getEncoded(), HexBin.decode(RDN_STYLE_2[i])));
        }

        rdn = style.fromString(DN_STYLE_3);
        for (int i = 0; i < rdn.length; i++) {
            assertTrue("testFromString", Arrays.equals(rdn[i].getEncoded(), HexBin.decode(RDN_STYLE_3[i])));
            assertTrue("testFromString", Arrays.equals(rdn[i].getEncoded(), HexBin.decode(RDN_STYLE_1[i])));
        }

    }

    public void testCalculateHashCode() {
        final X500NameStyle style = CFCAStyle.INSTANCE;
        assertTrue("testCalculateHashCode", 76690332 == style.calculateHashCode(new X500Name(style, DN_STYLE_1)));
        assertTrue("testCalculateHashCode", 76690332 == style.calculateHashCode(new X500Name(style, DN_STYLE_2)));
        assertTrue("testCalculateHashCode", 76690332 == style.calculateHashCode(new X500Name(style, DN_STYLE_3)));
    }

    public void testToStringX500Name() {
        final X500NameStyle style = CFCAStyle.INSTANCE;
        assertTrue("testToStringX500Name",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@民生RSA@12323232323232@1".equals(style.toString(new X500Name(style, DN_STYLE_1))));
        assertTrue("testToStringX500Name",
                "CN=051@民生RSA@12323232323232@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN".equals(style.toString(new X500Name(style, DN_STYLE_2))));
        assertTrue("testToStringX500Name",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@民生RSA@12323232323232@1".equals(style.toString(new X500Name(style, DN_STYLE_3))));

    }

    public void testOidToDisplayName() {
        final X500NameStyle style = CFCAStyle.INSTANCE;

        String name = null;
        for (int i = 0; i < attributeTypeOIDs.length; i++) {
            name = style.oidToDisplayName(attributeTypeOIDs[i]);

            if (!name.equals(displayNames[i])) {
                fail(String.format("testOidToDisplayName: (%s,%s)", name, displayNames[i]));
            }

        }

    }

    public void testOidToAttrNames() {
        final X500NameStyle style = CFCAStyle.INSTANCE;

        String[] name = null;
        for (int i = 0; i < attributeTypeOIDs.length; i++) {
            name = style.oidToAttrNames(attributeTypeOIDs[i]);
            if (!name[0].equals(attributeTypes[i])) {
                if ("sn".equals(attributeTypes[i]) && "serialnumber".equals(name[0])) {
                    continue;
                }
                if ("e".equals(attributeTypes[i]) && "emailaddress".equals(name[0])) {
                    continue;
                }

                fail(String.format("testOidToAttrNames: (%s,%s)", name[0], attributeTypes[i]));
            }

        }
    }

}
