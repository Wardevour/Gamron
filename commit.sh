#!/bin/bash
printf "Enter the name of the repo directory: "
read repopath
cd $repopath

git branch
printf "Enter the branch you want to commit to: "
read branch
git checkout $branch

git pull

git status

printf "\nWould you like to add tracked files and commit? y/n "
read ans1

if [ $ans1 = "y" ]; then
	printf "\nEnter Commit Description: "
	read comsg
	git commit -am "$comsg"
else
	git status -s
	printf "\nHow many files do you wish to add? "
	read addnum
	
	count=1
	while [[ $count -le $addnum ]]
	do
		git status -s
		printf "\nEnter file $count : "
		read filenme
		git add $filenme
		((count++))
	done
	
	printf "\nEnter Commit Description: "
	read comsg
	git commit -m "$comsg"
fi

printf "\nWould you like to push to $branch? y/n "
read ans2

if [ $ans2 = "y" ]; then
echo ""
git push origin $branch
else
printf "\nPUSH CANCELED"
fi
