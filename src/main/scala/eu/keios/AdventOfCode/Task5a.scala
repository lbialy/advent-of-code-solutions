package eu.keios.AdventOfCode

//  --- Day 5: Doesn't He Have Intern-Elves For This? ---
//
//  Santa needs help figuring out which strings in his text file are naughty or nice.
//
//  A nice string is one with all of the following properties:
//
//  It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
//  It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
//  It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
//  For example:
//
//  ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
//  aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
//  jchzalrnumimnmhp is naughty because it has no double letter.
//  haegwjzuvuyypxyu is naughty because it contains the string xy.
//  dvszwmarrgswjxmb is naughty because it contains only one vowel.
//  How many strings are nice?

import scala.annotation.tailrec

object Task5a extends Task5a {
  def result = taskInput.split("\n").map{ _.isNice }.count(identity)
}

trait Task5a extends AdventOfCodeTask {

  val niceVowels = List('a', 'e', 'i', 'o', 'u')

  val naughtyStrings = List("ab", "cd", "pq", "xy")

  def taskInput = "sszojmmrrkwuftyv\nisaljhemltsdzlum\nfujcyucsrxgatisb\nqiqqlmcgnhzparyg\noijbmduquhfactbc\njqzuvtggpdqcekgk\nzwqadogmpjmmxijf\nuilzxjythsqhwndh\ngtssqejjknzkkpvw\nwrggegukhhatygfi\nvhtcgqzerxonhsye\ntedlwzdjfppbmtdx\niuvrelxiapllaxbg\nfeybgiimfthtplui\nqxmmcnirvkzfrjwd\nvfarmltinsriqxpu\noanqfyqirkraesfq\nxilodxfuxphuiiii\nyukhnchvjkfwcbiq\nbdaibcbzeuxqplop\nivegnnpbiyxqsion\nybahkbzpditgwdgt\ndmebdomwabxgtctu\nibtvimgfaeonknoh\njsqraroxudetmfyw\ndqdbcwtpintfcvuz\ntiyphjunlxddenpj\nfgqwjgntxagidhah\nnwenhxmakxqkeehg\nzdoheaxqpcnlhnen\ntfetfqojqcdzlpbm\nqpnxkuldeiituggg\nxwttlbdwxohahwar\nhjkwzadmtrkegzye\nkoksqrqcfwcaxeof\nwulwmrptktliyxeq\ngyufbedqhhyqgqzj\ntxpunzodohikzlmj\njloqfuejfkemcrvu\namnflshcheuddqtc\npdvcsduggcogbiia\nyrioavgfmeafjpcz\nuyhbtmbutozzqfvq\nmwhgfwsgyuwcdzik\nauqylgxhmullxpaa\nlgelzivplaeoivzh\nuyvcepielfcmswoa\nqhirixgwkkccuzlp\nzoonniyosmkeejfg\niayfetpixkedyana\nictqeyzyqswdskiy\nejsgqteafvmorwxe\nlhaiqrlqqwfbrqdx\nydjyboqwhfpqfydc\ndwhttezyanrnbybv\nedgzkqeqkyojowvr\nrmjfdwsqamjqehdq\nozminkgnkwqctrxz\nbztjhxpjthchhfcd\nvrtioawyxkivrpiq\ndpbcsznkpkaaclyy\nvpoypksymdwttpvz\nhhdlruwclartkyap\nbqkrcbrksbzcggbo\njerbbbnxlwfvlaiw\ndwkasufidwjrjfbf\nkkfxtjhbnmqbmfwf\nvmnfziwqxmioukmj\nrqxvcultipkecdtu\nfhmfdibhtjzkiqsd\nhdpjbuzzbyafqrpd\nemszboysjuvwwvts\nmsyigmwcuybfiooq\ndruyksfnbluvnwoh\nfvgstvynnfbvxhsx\nbmzalvducnqtuune\nlzwkzfzttsvpllei\nolmplpvjamynfyfd\npadcwfkhystsvyfb\nwjhbvxkwtbfqdilb\nhruaqjwphonnterf\nbufjobjtvxtzjpmj\noiedrjvmlbtwyyuy\nsgiemafwfztwsyju\nnsoqqfudrtwszyqf\nvonbxquiiwxnazyl\nyvnmjxtptujwqudn\nrrnybqhvrcgwvrkq\ntaktoxzgotzxntfu\nquffzywzpxyaepxa\nrfvjebfiddcfgmwv\niaeozntougqwnzoh\nscdqyrhoqmljhoil\nbfmqticltmfhxwld\nbrbuktbyqlyfpsdl\noidnyhjkeqenjlhd\nkujsaiqojopvrygg\nvebzobmdbzvjnjtk\nuunoygzqjopwgmbg\npiljqxgicjzgifso\nikgptwcjzywswqnw\npujqsixoisvhdvwi\ntrtuxbgigogfsbbk\nmplstsqclhhdyaqk\ngzcwflvmstogdpvo\ntfjywbkmimyyqcjd\ngijutvhruqcsiznq\nibxkhjvzzxgavkha\nbtnxeqvznkxjsgmq\ntjgofgauxaelmjoq\nsokshvyhlkxerjrv\nltogbivktqmtezta\nuduwytzvqvfluyuf\nmsuckpthtgzhdxan\nfqmcglidvhvpirzr\ngwztkqpcwnutvfga\nbsjfgsrntdhlpqbx\nxloczbqybxmiopwt\norvevzyjliomkkgu\nmzjbhmfjjvaziget\ntlsdxuhwdmghdyjb\natoecyjhwmznaewi\npyxpyvvipbqibiox\najbfmpqqobfsmesj\nsiknbzefjblnohgd\neqfhgewbblwdfkmc\nopylbscrotckkrbk\nlbwxbofgjkzdxkle\nceixfjstaptdomvm\nhnkrqxifjmmjktie\naqykzeuzvvetoygd\nfouahjimfcisxima\nprkzhutbqsyrhjzx\nqqwliakathnsbzne\nsayhgqtlcqqidqhj\nygduolbysehdudra\nzricvxhdzznuxuce\nucvzakslykpgsixd\nudirhgcttmyspgsb\nyuwzppjzfsjhhdzi\ngtqergjiuwookwre\nxvxexbjyjkxovvwf\nmlpaqhnnkqxrmwmm\nezuqbrjozwuqafhb\nmcarusdthcbsonoq\nweeguqeheeiigrue\npngtfugozxofaqxv\ncopphvbjcmfspenv\njiyahihykjjkdaya\ngdqnmesvptuyrfwp\nvbdscfywqmfxbohh\ncrtrfuxyjypzubrg\nseihvevtxywxhflp\nfvvpmgttnapklwou\nqmqaqsajmqwhetpk\nzetxvrgjmblxvakr\nkpvwblrizaabmnhz\nmwpvvzaaicntrkcp\nclqyjiegtdsswqfm\nymrcnqgcpldgfwtm\nnzyqpdenetncgnwq\ncmkzevgacnmdkqro\nkzfdsnamjqbeirhi\nkpxrvgvvxapqlued\nrzskbnfobevzrtqu\nvjoahbfwtydugzap\nykbbldkoijlvicbl\nmfdmroiztsgjlasb\nquoigfyxwtwprmdr\nekxjqafwudgwfqjm\nobtvyjkiycxfcdpb\nlhoihfnbuqelthof\neydwzitgxryktddt\nrxsihfybacnpoyny\nbsncccxlplqgygtw\nrvmlaudsifnzhcqh\nhuxwsyjyebckcsnn\ngtuqzyihwhqvjtes\nzreeyomtngvztveq\nnwddzjingsarhkxb\nnuqxqtctpoldrlsh\nwkvnrwqgjooovhpf\nkwgueyiyffudtbyg\ntpkzapnjxefqnmew\nludwccvkihagvxal\nlfdtzhfadvabghna\nnjqmlsnrkcfhtvbb\ncajzbqleghhnlgap\nvmitdcozzvqvzatp\neelzefwqwjiywbcz\nuyztcuptfqvymjpi\naorhnrpkjqqtgnfo\nlfrxfdrduoeqmwwp\nvszpjvbctblplinh\nzexhadgpqfifcqrz\nueirfnshekpemqua\nqfremlntihbwabtb\nnwznunammfexltjc\nzkyieokaaogjehwt\nvlrxgkpclzeslqkq\nxrqrwfsuacywczhs\nolghlnfjdiwgdbqc\ndifnlxnedpqcsrdf\ndgpuhiisybjpidsj\nvlwmwrikmitmoxbt\nsazpcmcnviynoktm\npratafauetiknhln\nilgteekhzwlsfwcn\nywvwhrwhkaubvkbl\nqlaxivzwxyhvrxcf\nhbtlwjdriizqvjfb\nnrmsononytuwslsa\nmpxqgdthpoipyhjc\nmcdiwmiqeidwcglk\nvfbaeavmjjemfrmo\nqzcbzmisnynzibrc\nshzmpgxhehhcejhb\nwirtjadsqzydtyxd\nqjlrnjfokkqvnpue\ndxawdvjntlbxtuqc\nwttfmnrievfestog\neamjfvsjhvzzaobg\npbvfcwzjgxahlrag\nomvmjkqqnobvnzkn\nlcwmeibxhhlxnkzv\nuiaeroqfbvlazegs\ntwniyldyuonfyzqw\nwgjkmsbwgfotdabi\nhnomamxoxvrzvtew\nycrcfavikkrxxfgw\nisieyodknagzhaxy\nmgzdqwikzullzyco\nmumezgtxjrrejtrs\nnwmwjcgrqiwgfqel\nwjgxmebfmyjnxyyp\ndurpspyljdykvzxf\nzuslbrpooyetgafh\nkuzrhcjwbdouhyme\nwyxuvbciodscbvfm\nkbnpvuqwmxwfqtqe\nzddzercqogdpxmft\nsigrdchxtgavzzjh\nlznjolnorbuddgcs\nycnqabxlcajagwbt\nbnaudeaexahdgxsj\nrlnykxvoctfwanms\njngyetkoplrstfzt\ntdpxknwacksotdub\nyutqgssfoptvizgr\nlzmqnxeqjfnsxmsa\niqpgfsfmukovsdgu\nqywreehbidowtjyz\niozamtgusdctvnkw\nielmujhtmynlwcfd\nhzxnhtbnmmejlkyf\nftbslbzmiqkzebtd\nbcwdqgiiizmohack\ndqhfkzeddjzbdlxu\nmxopokqffisxosci\nvciatxhtuechbylk\nkhtkhcvelidjdena\nblatarwzfqcapkdt\nelamngegnczctcck\nxeicefdbwrxhuxuf\nsawvdhjoeahlgcdr\nkmdcimzsfkdfpnir\naxjayzqlosrduajb\nmfhzreuzzumvoggr\niqlbkbhrkptquldb\nxcvztvlshiefuhgb\npkvwyqmyoazocrio\najsxkdnerbmhyxaj\ntudibgsbnpnizvsi\ncxuiydkgdccrqvkh\ncyztpjesdzmbcpot\nnnazphxpanegwitx\nuphymczbmjalmsct\nyyxiwnlrogyzwqmg\ngmqwnahjvvdyhnfa\nutolskxpuoheugyl\nmseszdhyzoyavepd\nycqknvbuvcjfgmlc\nsknrxhxbfpvpeorn\nzqxqjetooqcodwml\nsesylkpvbndrdhsy\nfryuxvjnsvnjrxlw\nmfxusewqurscujnu\nmbitdjjtgzchvkfv\nozwlyxtaalxofovd\nwdqcduaykxbunpie\nrlnhykxiraileysk\nwgoqfrygttlamobg\nkflxzgxvcblkpsbz\ntmkisflhativzhde\nowsdrfgkaamogjzd\ngaupjkvkzavhfnes\nwknkurddcknbdleg\nlltviwincmbtduap\nqwzvspgbcksyzzmb\nydzzkumecryfjgnk\njzvmwgjutxoysaam\nicrwpyhxllbardkr\njdopyntshmvltrve\nafgkigxcuvmdbqou\nmfzzudntmvuyhjzt\nduxhgtwafcgrpihc\ntsnhrkvponudumeb\nsqtvnbeiigdzbjgv\neczmkqwvnsrracuo\nmhehsgqwiczaiaxv\nkaudmfvifovrimpd\nlupikgivechdbwfr\nmwaaysrndiutuiqx\naacuiiwgaannunmm\ntjqjbftaqitukwzp\nlrcqyskykbjpaekn\nlirrvofbcqpjzxmr\njurorvzpplyelfml\nqonbllojmloykjqe\nsllkzqujfnbauuqp\nauexjwsvphvikali\nusuelbssqmbrkxyc\nwyuokkfjexikptvv\nwmfedauwjgbrgytl\nsfwvtlzzebxzmuvw\nrdhqxuechjsjcvaf\nkpavhqkukugocsxu\novnjtumxowbxduts\nzgerpjufauptxgat\npevvnzjfwhjxdoxq\npmmfwxajgfziszcs\ndifmeqvaghuitjhs\nicpwjbzcmlcterwm\nngqpvhajttxuegyh\nmosjlqswdngwqsmi\nfrlvgpxrjolgodlu\neazwgrpcxjgoszeg\nbbtsthgkjrpkiiyk\ntjonoglufuvsvabe\nxhkbcrofytmbzrtk\nkqftfzdmpbxjynps\nkmeqpocbnikdtfyv\nqjjymgqxhnjwxxhp\ndmgicrhgbngdtmjt\nzdxrhdhbdutlawnc\nafvoekuhdboxghvx\nhiipezngkqcnihty\nbbmqgheidenweeov\nsuprgwxgxwfsgjnx\nadeagikyamgqphrj\nzzifqinoeqaorjxg\nadhgppljizpaxzld\nlvxyieypvvuqjiyc\nnljoakatwwwoovzn\nfcrkfxclcacshhmx\nownnxqtdhqbgthch\nlmfylrcdmdkgpwnj\nhlwjfbvlswbzpbjr\nmkofhdtljdetcyvp\nsynyxhifbetzarpo\nagnggugngadrcxoc\nuhttadmdmhidpyjw\nohfwjfhunalbubpr\npzkkkkwrlvxiuysn\nkmidbxmyzkjrwjhu\negtitdydwjxmajnw\ncivoeoiuwtwgbqqs\ndfptsguzfinqoslk\ntdfvkreormspprer\nzvnvbrmthatzztwi\nffkyddccrrfikjde\nhrrmraevdnztiwff\nqaeygykcpbtjwjbr\npurwhitkmrtybslh\nqzziznlswjaussel\ndfcxkvdpqccdqqxj\ntuotforulrrytgyn\ngmtgfofgucjywkev\nwkyoxudvdkbgpwhd\nqbvktvfvipftztnn\notckgmojziezmojb\ninxhvzbtgkjxflay\nqvxapbiatuudseno\nkrpvqosbesnjntut\noqeukkgjsfuqkjbb\nprcjnyymnqwqksiz\nvuortvjxgckresko\norqlyobvkuwgathr\nqnpyxlnazyfuijox\nzwlblfkoklqmqzkw\nhmwurwtpwnrcsanl\njzvxohuakopuzgpf\nsfcpnxrviphhvxmx\nqtwdeadudtqhbely\ndbmkmloasqphnlgj\nolylnjtkxgrubmtk\nnxsdbqjuvwrrdbpq\nwbabpirnpcsmpipw\nhjnkyiuxpqrlvims\nenzpntcjnxdpuqch\nvvvqhlstzcizyimn\ntriozhqndbttglhv\nfukvgteitwaagpzx\nuhcvukfbmrvskpen\ntizcyupztftzxdmt\nvtkpnbpdzsaluczz\nwodfoyhoekidxttm\notqocljrmwfqbxzu\nlinfbsnfvixlwykn\nvxsluutrwskslnye\nzbshygtwugixjvsi\nzdcqwxvwytmzhvoo\nwrseozkkcyctrmei\nfblgtvogvkpqzxiy\nopueqnuyngegbtnf\nqxbovietpacqqxok\nzacrdrrkohfygddn\ngbnnvjqmkdupwzpq\nqgrgmsxeotozvcak\nhnppukzvzfmlokid\ndzbheurndscrrtcl\nwbgdkadtszebbrcw\nfdmzppzphhpzyuiz\nbukomunhrjrypohj\nohodhelegxootqbj\nrsplgzarlrknqjyh\npunjjwpsxnhpzgvu\ndjdfahypfjvpvibm\nmlgrqsmhaozatsvy\nxwktrgyuhqiquxgn\nwvfaoolwtkbrisvf\nplttjdmguxjwmeqr\nzlvvbwvlhauyjykw\ncigwkbyjhmepikej\nmasmylenrusgtyxs\nhviqzufwyetyznze\nnzqfuhrooswxxhus\npdbdetaqcrqzzwxf\noehmvziiqwkzhzib\nicgpyrukiokmytoy\nooixfvwtiafnwkce\nrvnmgqggpjopkihs\nwywualssrmaqigqk\npdbvflnwfswsrirl\njeaezptokkccpbuj\nmbdwjntysntsaaby\nldlgcawkzcwuxzpz\nlwktbgrzswbsweht\necspepmzarzmgpjm\nqmfyvulkmkxjncai\nizftypvwngiukrns\nzgmnyjfeqffbooww\nnyrkhggnprhedows\nyykzzrjmlevgffah\nmavaemfxhlfejfki\ncmegmfjbkvpncqwf\nzxidlodrezztcrij\nfseasudpgvgnysjv\nfupcimjupywzpqzp\niqhgokavirrcvyys\nwjmkcareucnmfhui\nnftflsqnkgjaexhq\nmgklahzlcbapntgw\nkfbmeavfxtppnrxn\nnuhyvhknlufdynvn\nnviogjxbluwrcoec\ntyozixxxaqiuvoys\nkgwlvmvgtsvxojpr\nmoeektyhyonfdhrb\nkahvevmmfsmiiqex\nxcywnqzcdqtvhiwd\nfnievhiyltbvtvem\njlmndqufirwgtdxd\nmuypbfttoeelsnbs\nrypxzbnujitfwkou\nubmmjbznskildeoj\nofnmizdeicrmkjxp\nrekvectjbmdnfcib\nyohrojuvdexbctdh\ngwfnfdeibynzjmhz\njfznhfcqdwlpjull\nscrinzycfhwkmmso\nmskutzossrwoqqsi\nrygoebkzgyzushhr\njpjqiycflqkexemx\narbufysjqmgaapnl\ndbjerflevtgweeoj\nsnybnnjlmwjvhois\nfszuzplntraprmbj\nmkvaatolvuggikvg\nzpuzuqygoxesnuyc\nwnpxvmxvllxalulm\neivuuafkvudeouwy\nrvzckdyixetfuehr\nqgmnicdoqhveahyx\nmiawwngyymshjmpj\npvckyoncpqeqkbmx\nllninfenrfjqxurv\nkzbjnlgsqjfuzqtp\nrveqcmxomvpjcwte\nbzotkawzbopkosnx\nktqvpiribpypaymu\nwvlzkivbukhnvram\nuohntlcoguvjqqdo\najlsiksjrcnzepkt\nxsqatbldqcykwusd\nihbivgzrwpmowkop\nvfayesfojmibkjpb\nuaqbnijtrhvqxjtb\nhhovshsfmvkvymba\njerwmyxrfeyvxcgg\nhncafjwrlvdcupma\nqyvigggxfylbbrzt\nhiiixcyohmvnkpgk\nmmitpwopgxuftdfu\niaxderqpceboixoa\nzodfmjhuzhnsqfcb\nsthtcbadrclrazsi\nbkkkkcwegvypbrio\nwmpcofuvzemunlhj\ngqwebiifvqoeynro\njuupusqdsvxcpsgv\nrbhdfhthxelolyse\nkjimpwnjfrqlqhhz\nrcuigrjzarzpjgfq\nhtxcejfyzhydinks\nsxucpdxhvqjxxjwf\nomsznfcimbcwaxal\ngufmtdlhgrsvcosb\nbssshaqujtmluerz\nuukotwjkstgwijtr\nkbqkneobbrdogrxk\nljqopjcjmelgrakz\nrwtfnvnzryujwkfb\ndedjjbrndqnilbeh\nnzinsxnpptzagwlb\nlwqanydfirhnhkxy\nhrjuzfumbvfccxno\nokismsadkbseumnp\nsfkmiaiwlktxqvwa\nhauwpjjwowbunbjj\nnowkofejwvutcnui\nbqzzppwoslaeixro\nurpfgufwbtzenkpj\nxgeszvuqwxeykhef\nyxoldvkyuikwqyeq\nonbbhxrnmohzskgg\nqcikuxakrqeugpoa\nlnudcqbtyzhlpers\nnxduvwfrgzaailgl\nxniuwvxufzxjjrwz\nljwithcqmgvntjdj\nawkftfagrfzywkhs\nuedtpzxyubeveuek\nbhcqdwidbjkqqhzl\niyneqjdmlhowwzxx\nkvshzltcrrururty\nzgfpiwajegwezupo\ntkrvyanujjwmyyri\nercsefuihcmoaiep\nienjrxpmetinvbos\njnwfutjbgenlipzq\nbgohjmrptfuamzbz\nrtsyamajrhxbcncw\ntfjdssnmztvbnscs\nbgaychdlmchngqlp\nkfjljiobynhwfkjo\nowtdxzcpqleftbvn\nltjtimxwstvzwzjj\nwbrvjjjajuombokf\nzblpbpuaqbkvsxye\ngwgdtbpnlhyqspdi\nabipqjihjqfofmkx\nnlqymnuvjpvvgova\navngotmhodpoufzn\nqmdyivtzitnrjuae\nxfwjmqtqdljuerxi\ncsuellnlcyqaaamq\nslqyrcurcyuoxquo\ndcjmxyzbzpohzprl\nuqfnmjwniyqgsowb\nrbmxpqoblyxdocqc\nebjclrdbqjhladem\nainnfhxnsgwqnmyo\neyytjjwhvodtzquf\niabjgmbbhilrcyyp\npqfnehkivuelyccc\nxgjbyhfgmtseiimt\njwxyqhdbjiqqqeyy\ngxsbrncqkmvaryln\nvhjisxjkinaejytk\nseexagcdmaedpcvh\nlvudfgrcpjxzdpvd\nfxtegyrqjzhmqean\ndnoiseraqcoossmc\nnwrhmwwbykvwmgep\nudmzskejvizmtlce\nhbzvqhvudfdlegaa\ncghmlfqejbxewskv\nbntcmjqfwomtbwsb\nqezhowyopjdyhzng\ntodzsocdkgfxanbz\nzgjkssrjlwxuhwbk\neibzljqsieriyrzr\nwamxvzqyycrxotjp\nepzvfkispwqynadu\ndwlpfhtrafrxlyie\nqhgzujhgdruowoug\ngirstvkahaemmxvh\nbaitcrqmxhazyhbl\nxyanqcchbhkajdmc\ngfvjmmcgfhvgnfdq\ntdfdbslwncbnkzyz\njojuselkpmnnbcbb\nhatdslkgxtqpmavj\ndvelfeddvgjcyxkj\ngnsofhkfepgwltse\nmdngnobasfpewlno\nqssnbcyjgmkyuoga\nglvcmmjytmprqwvn\ngwrixumjbcdffsdl\nlozravlzvfqtsuiq\nsicaflbqdxbmdlch\ninwfjkyyqbwpmqlq\ncuvszfotxywuzhzi\nigfxyoaacoarlvay\nucjfhgdmnjvgvuni\nrvvkzjsytqgiposh\njduinhjjntrmqroz\nyparkxbgsfnueyll\nlyeqqeisxzfsqzuj\nwoncskbibjnumydm\nlltucklragtjmxtl\nubiyvmyhlesfxotj\nuecjseeicldqrqww\nxxlxkbcthufnjbnm\nlhqijovvhlffpxga\nfzdgqpzijitlogjz\nefzzjqvwphomxdpd\njvgzvuyzobeazssc\nhejfycgxywfjgbfw\nyhjjmvkqfbnbliks\nsffvfyywtlntsdsz\ndwmxqudvxqdenrur\nasnukgppdemxrzaz\nnwqfnumblwvdpphx\nkqsmkkspqvxzuket\ncpnraovljzqiquaz\nqrzgrdlyyzbyykhg\nopoahcbiydyhsmqe\nhjknnfdauidjeydr\nhczdjjlygoezadow\nrtflowzqycimllfv\nsfsrgrerzlnychhq\nbpahuvlblcolpjmj\nalbgnjkgmcrlaicl\npijyqdhfxpaxzdex\neeymiddvcwkpbpux\nrqwkqoabywgggnln\nvckbollyhgbgmgwh\nylzlgvnuvpynybkm\nhpmbxtpfosbsjixt\nocebeihnhvkhjfqz\ntvctyxoujdgwayze\nefvhwxtuhapqxjen\nrusksgefyidldmpo\nnkmtjvddfmhirmzz\nwhvtsuadwofzmvrt\niiwjqvsdxudhdzzk\ngucirgxaxgcassyo\nrmhfasfzexeykwmr\nhynlxcvsbgosjbis\nhuregszrcaocueen\npifezpoolrnbdqtv\nunatnixzvdbqeyox\nxtawlpduxgacchfe\nbdvdbflqfphndduf\nxtdsnjnmzccfptyt\nnkhsdkhqtzqbphhg\naqcubmfkczlaxiyb\nmoziflxpsfubucmv\nsrdgnnjtfehiimqx\npwfalehdfyykrohf\nsysxssmvewyfjrve\nbrsemdzosgqvvlxe\nbimbjoshuvflkiat\nhkgjasmljkpkwwku\nsbnmwjvodygobpqc\nbbbqycejueruihhd\ncorawswvlvneipyc\ngcyhknmwsczcxedh\nkppakbffdhntmcqp\nynulzwkfaemkcefp\npyroowjekeurlbii\niwksighrswdcnmxf\nglokrdmugreygnsg\nxkmvvumnfzckryop\naesviofpufygschi\ncsloawlirnegsssq\nfkqdqqmlzuxbkzbc\nuzlhzcfenxdfjdzp\npoaaidrktteusvyf\nzrlyfzmjzfvivcfr\nqwjulskbniitgqtx\ngjeszjksbfsuejki\nvczdejdbfixbduaq\nknjdrjthitjxluth\njweydeginrnicirl\nbottrfgccqhyycsl\neiquffofoadmbuhk\nlbqfutmzoksscswf\nxfmdvnvfcnzjprba\nuvugkjbkhlaoxmyx\nwadlgtpczgvcaqqv\ninzrszbtossflsxk\ndbzbtashaartczrj\nqbjiqpccefcfkvod\nhluujmokjywotvzy\nthwlliksfztcmwzh\narahybspdaqdexrq\nnuojrmsgyipdvwyx\nhnajdwjwmzattvst\nsulcgaxezkprjbgu\nrjowuugwdpkjtypw\noeugzwuhnrgiaqga\nwvxnyymwftfoswij\npqxklzkjpcqscvde\ntuymjzknntekglqj\nodteewktugcwlhln\nexsptotlfecmgehc\neeswfcijtvzgrqel\nvjhrkiwmunuiwqau\nzhlixepkeijoemne\npavfsmwesuvebzdd\njzovbklnngfdmyws\nnbajyohtzfeoiixz\nciozmhrsjzrwxvhz\ngwucrxieqbaqfjuv\nuayrxrltnohexawc\nflmrbhwsfbcquffm\ngjyabmngkitawlxc\nrwwtggvaygfbovhg\nxquiegaisynictjq\noudzwuhexrwwdbyy\nlengxmguyrwhrebb\nuklxpglldbgqsjls\ndbmvlfeyguydfsxq\nzspdwdqcrmtmdtsc\nmqfnzwbfqlauvrgc\namcrkzptgacywvhv\nndxmskrwrqysrndf\nmwjyhsufeqhwisju\nsrlrukoaenyevykt\ntnpjtpwawrxbikct\ngeczalxmgxejulcv\ntvkcbqdhmuwcxqci\ntiovluvwezwwgaox\nzrjhtbgajkjqzmfo\nvcrywduwsklepirs\nlofequdigsszuioy\nwxsdzomkjqymlzat\niabaczqtrfbmypuy\nibdlmudbajikcncr\nrqcvkzsbwmavdwnv\nypxoyjelhllhbeog\nfdnszbkezyjbttbg\nuxnhrldastpdjkdz\nxfrjbehtxnlyzcka\nomjyfhbibqwgcpbv\neguucnoxaoprszmp\nxfpypldgcmcllyzz\naypnmgqjxjqceelv\nmgzharymejlafvgf\ntzowgwsubbaigdok\nilsehjqpcjwmylxc\npfmouwntfhfnmrwk\ncsgokybgdqwnduwp\neaxwvxvvwbrovypz\nnmluqvobbbmdiwwb\nlnkminvfjjzqbmio\nmjiiqzycqdhfietz\ntowlrzriicyraevq\nobiloewdvbrsfwjo\nlmeooaajlthsfltw\nichygipzpykkesrw\ngfysloxmqdsfskvt\nsaqzntehjldvwtsx\npqddoemaufpfcaew\nmjrxvbvwcreaybwe\nngfbrwfqnxqosoai\nnesyewxreiqvhald\nkqhqdlquywotcyfy\nliliptyoqujensfi\nnsahsaxvaepzneqq\nzaickulfjajhctye\ngxjzahtgbgbabtht\nkoxbuopaqhlsyhrp\njhzejdjidqqtjnwe\ndekrkdvprfqpcqki\nlinwlombdqtdeyop\ndvckqqbnigdcmwmx\nyaxygbjpzkvnnebv\nrlzkdkgaagmcpxah\ncfzuyxivtknirqvt\nobivkajhsjnrxxhn\nlmjhayymgpseuynn\nbbjyewkwadaipyju\nlmzyhwomfypoftuu\ngtzhqlgltvatxack\njfflcfaqqkrrltgq\ntxoummmnzfrlrmcg\nohemsbfuqqpucups\nimsfvowcbieotlok\ntcnsnccdszxfcyde\nqkcdtkwuaquajazz\narcfnhmdjezdbqku\nsrnocgyqrlcvlhkb\nmppbzvfmcdirbyfw\nxiuarktilpldwgwd\nypufwmhrvzqmexpc\nitpdnsfkwgrdujmj\ncmpxnodtsswkyxkr\nwayyxtjklfrmvbfp\nmfaxphcnjczhbbwy\nsjxhgwdnqcofbdra\npnxmujuylqccjvjm\nivamtjbvairwjqwl\ndeijtmzgpfxrclss\nbzkqcaqagsynlaer\ntycefobvxcvwaulz\nctbhnywezxkdsswf\nurrxxebxrthtjvib\nfpfelcigwqwdjucv\nngfcyyqpqulwcphb\nrltkzsiipkpzlgpw\nqfdsymzwhqqdkykc\nbalrhhxipoqzmihj\nrnwalxgigswxomga\nghqnxeogckshphgr\nlyyaentdizaumnla\nexriodwfzosbeoib\nspeswfggibijfejk\nyxmxgfhvmshqszrq\nhcqhngvahzgawjga\nqmhlsrfpesmeksur\neviafjejygakodla\nkvcfeiqhynqadbzv\nfusvyhowslfzqttg\ngirqmvwmcvntrwau\nyuavizroykfkdekz\njmcwohvmzvowrhxf\nkzimlcpavapynfue\nwjudcdtrewfabppq\nyqpteuxqgbmqfgxh\nxdgiszbuhdognniu\njsguxfwhpftlcjoh\nwhakkvspssgjzxre\nggvnvjurlyhhijgm\nkrvbhjybnpemeptr\npqedgfojyjybfbzr\njzhcrsgmnkwwtpdo\nyyscxoxwofslncmp\ngzjhnxytmyntzths\niteigbnqbtpvqumi\nzjevfzusnjukqpfw\nxippcyhkfuounxqk\nmcnhrcfonfdgpkyh\npinkcyuhjkexbmzj\nlotxrswlxbxlxufs\nfmqajrtoabpckbnu\nwfkwsgmcffdgaqxg\nqfrsiwnohoyfbidr\nczfqbsbmiuyusaqs\nieknnjeecucghpoo\ncevdgqnugupvmsge\ngjkajcyjnxdrtuvr\nudzhrargnujxiclq\nzqqrhhmjwermjssg\nggdivtmgoqajydzz\nwnpfsgtxowkjiivl\nafbhqawjbotxnqpd\nxjpkifkhfjeqifdn\noyfggzsstfhvticp\nkercaetahymeawxy\nkhphblhcgmbupmzt\niggoqtqpvaebtiol\nofknifysuasshoya\nqxuewroccsbogrbv\napsbnbkiopopytgu\nzyahfroovfjlythh\nbxhjwfgeuxlviydq\nuvbhdtvaypasaswa\nqamcjzrmesqgqdiz\nhjnjyzrxntiycyel\nwkcrwqwniczwdxgq\nhibxlvkqakusswkx\nmzjyuenepwdgrkty\ntvywsoqslfsulses\njqwcwuuisrclircv\nxanwaoebfrzhurct\nykriratovsvxxasf\nqyebvtqqxbjuuwuo\ntelrvlwvriylnder\nacksrrptgnhkeiaa\nyemwfjhiqlzsvdxf\nbanrornfkcymmkcc\nytbhxvaeiigjpcgm\ncrepyazgxquposkn\nxlqwdrytzwnxzwzv\nxtrbfbwopxscftps\nkwbytzukgseeyjla\nqtfdvavvjogybxjg\nytbmvmrcxwfkgvzw\nnbscbdskdeocnfzr\nsqquwjbdxsxhcseg\newqxhigqcgszfsuw\ncvkyfcyfmubzwsee\ndcoawetekigxgygd\nohgqnqhfimyuqhvi\notisopzzpvnhctte\nbauieohjejamzien\newnnopzkujbvhwce\naeyqlskpaehagdiv\npncudvivwnnqspxy\nytugesilgveokxcg\nzoidxeelqdjesxpr\nducjccsuaygfchzj\nsmhgllqqqcjfubfc\nnlbyyywergronmir\nprdawpbjhrzsbsvj\nnmgzhnjhlpcplmui\neflaogtjghdjmxxz\nqolvpngucbkprrdc\nixywxcienveltgho\nmwnpqtocagenkxut\niskrfbwxonkguywx\nouhtbvcaczqzmpua\nsrewprgddfgmdbao\ndyufrltacelchlvu\nczmzcbrkecixuwzz\ndtbeojcztzauofuk\nprrgoehpqhngfgmw\nbaolzvfrrevxsyke\nzqadgxshwiarkzwh\nvsackherluvurqqj\nsurbpxdulvcvgjbd\nwqxytarcxzgxhvtx\nvbcubqvejcfsgrac\nzqnjfeapshjowzja\nhekvbhtainkvbynx\nknnugxoktxpvoxnh\nknoaalcefpgtvlwm\nqoakaunowmsuvkus\nypkvlzcduzlezqcb\nujhcagawtyepyogh\nwsilcrxncnffaxjf\ngbbycjuscquaycrk\naduojapeaqwivnly\nceafyxrakviagcjy\nnntajnghicgnrlst\nvdodpeherjmmvbje\nwyyhrnegblwvdobn\nxlfurpghkpbzhhif\nxyppnjiljvirmqjo\nkglzqahipnddanpi\nomjateouxikwxowr\nocifnoopfglmndcx\nemudcukfbadyijev\nooktviixetfddfmh\nwtvrhloyjewdeycg\ncgjncqykgutfjhvb\nnkwvpswppeffmwad\nhqbcmfhzkxmnrivg\nmdskbvzguxvieilr\nanjcvqpavhdloaqh\nerksespdevjylenq\nfadxwbmisazyegup\niyuiffjmcaahowhj\nygkdezmynmltodbv\nfytneukxqkjattvh\nwoerxfadbfrvdcnz\niwsljvkyfastccoa\nmovylhjranlorofe\ndrdmicdaiwukemep\nknfgtsmuhfcvvshg\nibstpbevqmdlhajn\ntstwsswswrxlzrqs\nestyydmzothggudf\njezogwvymvikszwa\nizmqcwdyggibliet\nnzpxbegurwnwrnca\nkzkojelnvkwfublh\nxqcssgozuxfqtiwi\ntcdoigumjrgvczfv\nikcjyubjmylkwlwq\nkqfivwystpqzvhan\nbzukgvyoqewniivj\niduapzclhhyfladn\nfbpyzxdfmkrtfaeg\nyzsmlbnftftgwadz"

  def hasAtLeastThreeNiceVowels(s: String): Boolean = {
    @tailrec def recFindNiceVowels(current: Char, rest: String, found: List[Char]): List[Char] = {
      val currentIsNice = niceVowels.contains(current)
      if (currentIsNice && rest.length > 0) {
        recFindNiceVowels(rest.head, rest.tail, current :: found)
      } else if (currentIsNice && rest.length == 0) {
        current :: found
      } else if (rest.length > 0) {
        recFindNiceVowels(rest.head, rest.tail, found)
      } else {
        found
      }
    }

    recFindNiceVowels(s.head, s.tail, Nil).size >= 3
  }

  def hasAtLeastOneNicePair(s: String): Boolean = {
    s.sliding(2).foldLeft(List.empty[String]) { (acc, curr) =>
      if (curr.head == curr.tail.head) {
        curr :: acc
      } else {
        acc
      }
    }.nonEmpty
  }

  def doesNotContainAnyNaughtyStrings(s: String): Boolean = {
    naughtyStrings.foldLeft(true) { (acc, curr) =>
      if (!acc) {
        //
        false
      } else if (s.contains(curr)) {
        false
      } else {
        true
      }
    }
  }

  implicit class SelfAssessingString(s: String) {
    def isNice: Boolean = hasAtLeastThreeNiceVowels(s) && hasAtLeastOneNicePair(s) && doesNotContainAnyNaughtyStrings(s)
  }

}
