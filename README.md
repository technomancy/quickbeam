# quickbeam

Quickbeam gives you access to git repositories via Clojure.

## Usage

    user=> (use 'quick.beam 'clojure.pprint)
    user=> (pprint (take 3 (history "~/src/quickbeam")))

    ({:author "Phil Hagelberg <technomancy@gmail.com> 1305253336 -0400",
      :message "Move from quickbeam.core to quick.beam.\n",
      :sha "d04857c62ed939b11e227d65c688b08d7c328b99",
      :date #<Date Thu May 12 22:22:16 EDT 2011>}
     {:author "Clojure <clojure@enigma.(none)> 1305249992 -0400",
      :message "Fix c.j.io/file usage.\n",
      :sha "21606560f88ea18da3d63383b252f32ae993038f",
      :date #<Date Thu May 12 21:26:32 EDT 2011>}
     {:author "Clojure <clojure@enigma.(none)> 1305249049 -0400",
      :message "Add default arg (cwd) to history.\n",
      :sha "8d06b5a4cdf99e055a140f3bb2d5735db9debd1a",
      :date #<Date Thu May 12 21:10:49 EDT 2011>})

That's all there is to it!

## License

Copyright (C) 2011 Phil Hagelberg and the Boston Clojure Group

Distributed under the Eclipse Public License, the same as Clojure.
