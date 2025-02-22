package com.jayway.jsonpath;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;
import java.util.regex.Pattern;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static com.jayway.jsonpath.Filter.parse;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterParseTest {

    @Test
    public void a_filter_can_be_parsed() {

        parse("[?(@.foo)]");
        parse("[?(@.foo == 1)]");
        parse("[?(@.foo == 1 || @['bar'])]");
        parse("[?(@.foo == 1 && @['bar'])]");
    }

    @Test
    public void an_invalid_filter_can_not_be_parsed() {
        try {
            parse("[?(@.foo == 1)");
            Assertions.fail("expected " + InvalidPathException.class.getName());
        } catch (InvalidPathException ipe){}

        try {
            parse("[?(@.foo == 1) ||]");
            Assertions.fail("expected " + InvalidPathException.class.getName());
        } catch (InvalidPathException ipe){}

        try {
            parse("[(@.foo == 1)]");
            Assertions.fail("expected " + InvalidPathException.class.getName());
        } catch (InvalidPathException ipe){}

        try {
            parse("[?@.foo == 1)]");
            Assertions.fail("expected " + InvalidPathException.class.getName());
        } catch (InvalidPathException ipe){}
    }


    @Test
    public void a_gte_filter_can_be_serialized() {

        String filter = filter(where("a").gte(1)).toString();
        String parsed = parse("[?(@['a'] >= 1)]").toString();

        assertThat(filter).isEqualTo(parse(parsed).toString());
    }

    @Test
    public void a_lte_filter_can_be_serialized() {

        String filter = filter(where("a").lte(1)).toString();
        String parsed = parse("[?(@['a'] <= 1)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_eq_filter_can_be_serialized() {

        String filter = filter(where("a").eq(1)).toString();
        String parsed = parse("[?(@['a'] == 1)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_ne_filter_can_be_serialized() {

        String filter = filter(where("a").ne(1)).toString();
        String parsed = parse("[?(@['a'] != 1)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_lt_filter_can_be_serialized() {

        String filter = filter(where("a").lt(1)).toString();
        String parsed = parse("[?(@['a'] < 1)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_ltall_filter_can_be_serialized() {
        String filter = filter(where("a").ltall(1)).toString();
        String parsed = parse("[?(@['a'] LTALL [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_lteall_filter_can_be_serialized() {
        String filter = filter(where("a").lteall(1)).toString();
        String parsed = parse("[?(@['a'] LTEALL [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_ltany_filter_can_be_serialized() {
        String filter = filter(where("a").ltany(1)).toString();
        String parsed = parse("[?(@['a'] LTANY [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_lteany_filter_can_be_serialized() {
        String filter = filter(where("a").lteany(1)).toString();
        String parsed = parse("[?(@['a'] LTEANY [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_gt_filter_can_be_serialized() {

        String filter = filter(where("a").gt(1)).toString();
        String parsed = parse("[?(@['a'] > 1)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_gtall_filter_can_be_serialized() {
        String filter = filter(where("a").gtall(1)).toString();
        String parsed = parse("[?(@['a'] GTALL [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_gteall_filter_can_be_serialized() {
        String filter = filter(where("a").gteall(1)).toString();
        String parsed = parse("[?(@['a'] GTEALL [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_gtany_filter_can_be_serialized() {
        String filter = filter(where("a").gtany(1)).toString();
        String parsed = parse("[?(@['a'] GTANY [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_gteany_filter_can_be_serialized() {
        String filter = filter(where("a").gteany(1)).toString();
        String parsed = parse("[?(@['a'] GTEANY [1])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_nin_filter_can_be_serialized() {
        String filter = filter(where("a").nin(1)).toString();
        String parsed = parse("[?(@['a'] NIN [1])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_in_filter_can_be_serialized() {

        String filter = filter(where("a").in("a")).toString();
        String parsed = parse("[?(@['a'] IN ['a'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_contains_filter_can_be_serialized() {
        String filter = filter(where("a").contains("a")).toString();
        String parsed = parse("[?(@['a'] CONTAINS 'a')]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_notcontains_filter_can_be_serialized() {
        String filter = filter(where("a").notcontains("a")).toString();
        String parsed = parse("[?(@['a'] NOTCONTAINS 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_all_filter_can_be_serialized() {

        String filter = filter(where("a").all("a", "b")).toString();
        String parsed = parse("[?(@['a'] ALL ['a','b'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_size_filter_can_be_serialized() {

        String filter = filter(where("a").size(5)).toString();
        String parsed = parse("[?(@['a'] SIZE 5)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_subsetof_filter_can_be_serialized() {

        String filter = filter(where("a").subsetof(Collections.emptyList())).toString();
        String parsed = parse("[?(@['a'] SUBSETOF [])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_subsetof_objectrange_filter_can_be_serialized() {

        String filter = filter(where("a").subsetof("a")).toString();
        String parsed = parse("[?(@['a'] SUBSETOF ['a'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_anyof_filter_can_be_serialized() {

        String filter = filter(where("a").anyof(Collections.emptyList())).toString();
        String parsed = parse("[?(@['a'] ANYOF [])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_anyof_objectrange_filter_can_be_serialized() {

        String filter = filter(where("a").anyof("a")).toString();
        String parsed = parse("[?(@['a'] ANYOF ['a'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_noneof_filter_can_be_serialized() {

        String filter = filter(where("a").noneof(Collections.emptyList())).toString();
        String parsed = parse("[?(@['a'] NONEOF [])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_noneof_objectrange_filter_can_be_serialized() {

        String filter = filter(where("a").noneof("a")).toString();
        String parsed = parse("[?(@['a'] NONEOF ['a'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_exists_filter_can_be_serialized() {

        Filter a = filter(where("a").exists(true));
        String filter = a.toString();
        String parsed = parse("[?(@['a'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_not_exists_filter_can_be_serialized() {

        String filter = filter(where("a").exists(false)).toString();
        String parsed = parse("[?(!@['a'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_type_filter_can_be_serialized() {
        assertThat(filter(where("a").type(String.class)).toString()).isEqualTo("[?(@['a'] TYPE java.lang.String)]");
    }

    @Test
    public void a_matches_filter_can_be_serialized() {
        Filter a = filter(where("x").eq(1000));

        assertThat(filter(where("a").matches(a)).toString()).isEqualTo("[?(@['a'] MATCHES [?(@['x'] == 1000)])]");
    }

    @Test
    public void a_not_empty_filter_can_be_serialized() {

        String filter = filter(where("a").empty(false)).toString();
        String parsed = parse("[?(@['a'] EMPTY false)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void and_filter_can_be_serialized() {

        String filter = filter(where("a").eq(1).and("b").eq(2)).toString();
        String parsed = parse("[?(@['a'] == 1 && @['b'] == 2)]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void in_string_filter_can_be_serialized() {

        String filter = filter(where("a").in("1","2")).toString();
        String parsed = parse("[?(@['a'] IN ['1','2'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_deep_path_filter_can_be_serialized() {

        String filter = filter(where("a.b.c").in("1", "2")).toString();
        String parsed = parse("[?(@['a']['b']['c'] IN ['1','2'])]").toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_regex_filter_can_be_serialized() {
        assertThat(filter(where("a").regex(Pattern.compile("/.*?/i"))).toString()).isEqualTo("[?(@['a'] =~ /.*?/i)]");
    }

    @Test
    public void a_doc_ref_filter_can_be_serialized() {
        Filter f = parse("[?(@.display-price <= $.max-price)]");
        assertThat(f.toString()).isEqualTo("[?(@['display-price'] <= $['max-price'])]");
    }

    @Test
    public void and_combined_filters_can_be_serialized() {

        Filter a = filter(where("a").eq(1));
        Filter b = filter(where("b").eq(2));
        Filter c = a.and(b);

        String filter = c.toString();
        String parsed = parse("[?(@['a'] == 1 && @['b'] == 2)]").toString();


        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void or_combined_filters_can_be_serialized() {

        Filter a = filter(where("a").eq(1));
        Filter b = filter(where("b").eq(2));
        Filter c = a.or(b);

        String filter = c.toString();
        Filter d = parse("[?(@['a'] == 1 || @['b'] == 2)]");
        String parsed = d.toString();

        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_allmatch_filter_can_be_serialized() {
        String filter = filter(where("a").allmatch(Collections.emptyList())).toString();
        String parsed = parse("[?(@['a'] ALLMATCH [])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_anymatch_filter_can_be_serialized() {
        String filter = filter(where("a").anymatch(Collections.emptyList())).toString();
        String parsed = parse("[?(@['a'] ANYMATCH [])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_nonematch_filter_can_be_serialized() {
        String filter = filter(where("a").nonematch(Collections.emptyList())).toString();
        String parsed = parse("[?(@['a'] NONEMATCH [])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_exactmatch_filter_can_be_serialized() {
        String filter = filter(where("a").exactmatch(Collections.emptyList())).toString();
        String parsed = parse("[?(@['a'] EXACTMATCH [])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_allmatch_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").allmatch("a")).toString();
        String parsed = parse("[?(@['a'] ALLMATCH ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_anymatch_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").anymatch("a")).toString();
        String parsed = parse("[?(@['a'] ANYMATCH ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_nonematch_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").nonematch("a")).toString();
        String parsed = parse("[?(@['a'] NONEMATCH ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_exactmatch_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").exactmatch("a")).toString();
        String parsed = parse("[?(@['a'] EXACTMATCH ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_dateeq_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").dateeq("a")).toString();
        String parsed = parse("[?(@['a'] DATEEQ 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_dayeq_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").dayeq("a")).toString();
        String parsed = parse("[?(@['a'] DAYEQ 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_dayin_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").dayin("a")).toString();
        String parsed = parse("[?(@['a'] DAYIN ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_montheq_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").montheq("a")).toString();
        String parsed = parse("[?(@['a'] MONTHEQ 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_monthin_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").monthin("a")).toString();
        String parsed = parse("[?(@['a'] MONTHIN ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_yeareq_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").yeareq("a")).toString();
        String parsed = parse("[?(@['a'] YEAREQ 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_yearin_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").yearin("a")).toString();
        String parsed = parse("[?(@['a'] YEARIN ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_before_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").before("a")).toString();
        String parsed = parse("[?(@['a'] BEFORE 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_after_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").after("a")).toString();
        String parsed = parse("[?(@['a'] AFTER 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_houreq_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").houreq("a")).toString();
        String parsed = parse("[?(@['a'] HOUREQ 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_hourin_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").hourin("a")).toString();
        String parsed = parse("[?(@['a'] HOURIN ['a'])]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_timebefore_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").timebefore("a")).toString();
        String parsed = parse("[?(@['a'] TIMEBEFORE 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_timeafter_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").timeafter("a")).toString();
        String parsed = parse("[?(@['a'] TIMEAFTER 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_windowin_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").windowin("a")).toString();
        String parsed = parse("[?(@['a'] WINDOWIN 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_windowout_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").windowout("a")).toString();
        String parsed = parse("[?(@['a'] WINDOWOUT 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_windowtimein_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").windowtimein("a")).toString();
        String parsed = parse("[?(@['a'] WINDOWTIMEIN 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }

    @Test
    public void a_windowtimeout_objectrange_filter_can_be_serialized() {
        String filter = filter(where("a").windowtimeout("a")).toString();
        String parsed = parse("[?(@['a'] WINDOWTIMEOUT 'a')]").toString();
        assertThat(filter).isEqualTo(parsed);
    }
}
