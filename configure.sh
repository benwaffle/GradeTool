# configures an example Java project folder

function display_help() {
	printf "This is a general configure script. See options below:
--help, -h
\tDisplays this output.
--initial, -i
\tSets up the initial directory hierarchy.
--manifest, -m [CLASS NAME]
\tSets up an initial manifest file, with optional class name.\n"
}

mf_file="manifest.mf"
manifest_level=0
mf_classname=""

for arg in "$@"; do
	case $arg in
	"--initial" | "-i") # set up initial hierarchy
		ds=(src src/com bin lib)
		for fld in ${ds[@]}; do
			if [ ! -e $fld ]; then mkdir $fld; fi
		done
		# populate src
		if [ ! -e $mf_file ]; then touch $mf_file; fi
		;;
	"--manifest" | "-m") # set up manifest file
		manifest_level=$(($manifest_level + 1))
		;;
	"--help" | "-h") # default
		display_help
		;;
	*)
		if [ $manifest_level == 1 ]; then
			mf_classname=$arg
			manifest_level=$(($manifest_level + 1))
		else
			printf "Unknown option. Pass --help or -h for more information.\n"
			exit 1
		fi
		;;
	esac
done

if [ $manifest_level -gt 0 ]; then # set up manifest
	classes=". "$(find lib -type f -name '*.jar' | tr '\n' ' ')
	echo "Manifest-Version: 1.0" > $mf_file # erase from start
	printf "Name: %s\n" "`basename "$(pwd)"`" >> $mf_file
	if [ $manifest_level -gt 1 ]; then
		printf "Main-Class: %s\n" $mf_classname >> $mf_file
	fi
	printf "Class-Path:" >> $mf_file
	printf " %s\n" "`echo $classes | fold -s -w 72 | tr '\n' '?' | sed -e 's/\?/\n /g'`" >> $mf_file
fi

if [ $# -eq 0 ]; then display_help; fi
