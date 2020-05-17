#!/usr/bin/perl
use Redis;
my $r = Redis->new;
$r->connect;
$r->ping || die "no server?";
$r->select("9");
$r->flushdb();
$i=0;
@AR=();
@A=();
while(<>) {
   chomp;
   if (/\S/) {
     $AR[$i++]=$_;
   }
   else {
     push(@A, [@AR]);
     @AR=();
     $i=0;
   }
} 
foreach $preg (@A){
   $act=$r->incr("quiz:tot");
   $r->sadd("quiz:set",$act);
   $r->hset("quiz:hash:$act","texto", @$preg[0]);
   print "texto: " . @$preg[0] . "\n";
   $r->hset("quiz:hash:$act","link", @$preg[1]);
   print "link: " . @$preg[1] . "\n";
   $lastIndex = @$preg - 1 ;
   $r->hset("quiz:hash:$act","well", @$preg[$lastIndex]);
   print "well: " . @$preg[$lastIndex] . "\n";
   for($t = 1,$i = 2; $i <  $lastIndex;  $i++, $t++) {
      $r->hset("quiz:hash:$act","op" . $t, @$preg[$i]);
      print "op" .  $t . ": ", @$preg[$i], "\n";
   }
}
