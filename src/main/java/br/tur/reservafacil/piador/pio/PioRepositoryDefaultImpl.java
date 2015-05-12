package br.tur.reservafacil.piador.pio;

import java.util.*;

public class PioRepositoryDefaultImpl implements PioRepository {

    private final static Map<String, List<Pio>> repos = new HashMap<>();

    public PioRepositoryDefaultImpl() {
	initRepos();
    }

    @Override
    public void save(Pio pio) {
	if (!repos.containsKey(pio.getUsername())) {
	    repos.put(pio.getUsername(), new ArrayList<>());
	}
	repos.get(pio.getUsername()).add(pio);
    }

    @Override
    public List<Pio> findByUsername(String username) {
	return repos.get(username);
    }

    private void initRepos() {
	usuariosPadrao().stream()
			.forEach(user -> {
		postsPadrao().stream()
				.map(p -> new Pio(user, p))
				.forEach(this::save);
	});
    }

    private static List<String> usuariosPadrao() {
	return Arrays.asList("@camilla", "@paula", "@danielle", "@aline");
    }

    private static List<String> postsPadrao() {
	return Arrays.asList(
			"The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words. If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages. The new common language will be more",
			"One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What's happened to me?\" he thought. It wasn't a dream. His room, a proper human",
			"The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks help fax my big quiz. Quick, Baz, get my woven flax jodhpurs! \"Now fax quiz Jack!\" my brave",
			"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally");
    }
}